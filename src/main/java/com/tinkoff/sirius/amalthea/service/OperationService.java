package com.tinkoff.sirius.amalthea.service;

import com.tinkoff.sirius.amalthea.dto.operation.request.CreateOperationRequestDTO;
import com.tinkoff.sirius.amalthea.dto.operation.request.UpdateOperationRequestDTO;
import com.tinkoff.sirius.amalthea.dto.operation.response.OperationResponseDTO;
import com.tinkoff.sirius.amalthea.mapper.OperationMapper;
import com.tinkoff.sirius.amalthea.model.Category;
import com.tinkoff.sirius.amalthea.model.CategoryType;
import com.tinkoff.sirius.amalthea.model.Operation;
import com.tinkoff.sirius.amalthea.model.Wallet;
import com.tinkoff.sirius.amalthea.repository.CategoryRepository;
import com.tinkoff.sirius.amalthea.repository.OperationRepository;
import com.tinkoff.sirius.amalthea.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class OperationService {
    // todo: привести к общему формату: -optional, вынести преобразования к дто и обратно в маппер

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;

    public OperationService(
            OperationRepository operationRepository,
            OperationMapper operationMapper,
            WalletRepository walletRepository,
            CategoryRepository categoryRepository) {
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<OperationResponseDTO> findAll(Long walletId) {
        List<Operation> operationList = operationRepository.findAllByWalletIdOrderByDateDesc(walletId);
        return operationList.stream().map(operationMapper::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public OperationResponseDTO findById(Long id) {
        Operation operation = operationRepository.findById(id).orElseThrow();
        return operationMapper.toResponseDTO(operation);
    }

    public OperationResponseDTO update(OperationRequestDTO operationRequestDTO, Long id) {
        Operation operation = operationRepository.findById(id).orElseThrow();
        /*Category category = categoryRepository.findByTypeAndName(
                operationRequestDTO.getCategoryRequestDTO().getType(),
                operationRequestDTO.getCategoryRequestDTO().getName()).orElseThrow();*/

        Wallet wallet = walletRepository.findById(operation.getWallet().getId()).orElseThrow();

        if (checkLimit(wallet, operation)) {
            log.error("Operation cannot be updated due to exceeding the limit: " + id);
            return null;
        }

        Category oldCategory = categoryRepository.findById(operation.getCategory().getId()).orElseThrow();
        Category newCategory = categoryRepository.findById(operationRequestDTO.getCategoryId()).orElseThrow();

        removeFromBalance(operation, oldCategory, wallet);

        operation.setDate(new Timestamp(operationRequestDTO.getDate()))
                .setAmount(operationRequestDTO.getAmount())
                .setCategory(newCategory);

        addToBalance(operation, newCategory, wallet);
        return operationMapper.toResponseDTO(operationRepository.save(operation));
    }

    public Long deleteById(Long id) {
        try {
            Operation operation = operationRepository.findById(id).orElseThrow();
            Wallet wallet = walletRepository.findById(operation.getWallet().getId()).orElseThrow();
            Category category = categoryRepository.findById(operation.getCategory().getId()).orElseThrow();

            removeFromBalance(operation, category, wallet);

            operationRepository.deleteById(id);

            return id;
        } catch (NoSuchElementException e) {
            log.error("Operation not found: " + id);
            return null;
        }
    }

    public OperationResponseDTO save(CreateOperationRequestDTO operationRequestDTO) {
        Wallet wallet = walletRepository.findById(operationRequestDTO.getWalletId()).orElseThrow();
        /*Category category = categoryRepository.findByTypeAndName(
                operationRequestDTO.getCategoryRequestDTO().getType(),
                operationRequestDTO.getCategoryRequestDTO().getName()).orElseThrow();*/ // must be optional

        Category category = categoryRepository.findById(operationRequestDTO.getCategoryId()).orElseThrow();

        Operation operation = new Operation()
                .setDate(new Timestamp(operationRequestDTO.getDate()))
                .setAmount(operationRequestDTO.getAmount())
                .setWallet(wallet)
                .setCategory(category);
        operation = operationRepository.save(operation);

        addToBalance(operation, category, wallet);

        return operationMapper.toResponseDTO(operationRepository.save(operation));
    }

    private boolean checkLimit(Wallet wallet, Operation operation) {
        BigDecimal limit = wallet.getLimit(),
                balance = wallet.getBalance(),
                amount = operation.getAmount();

        BigDecimal newBalance = balance.add(amount);
        // if newBalance > limit, then true
        return newBalance.compareTo(limit) > 0;
    }

    private void addToBalance(Operation operation, Category category, Wallet wallet) {
        if (checkLimit(wallet, operation)) {
            log.error("Operation cannot be added due to exceeding the limit");
            return;
        }

        BigDecimal balance = wallet.getBalance(),
                amount = operation.getAmount();

        if (category.getType() == CategoryType.INCOME) {

            wallet.setIncome(wallet.getIncome().add(amount));
            wallet.setBalance(balance.add(amount));

        } else if (category.getType() == CategoryType.EXPENSE) {

            wallet.setExpense(wallet.getExpense().add(amount));
            wallet.setBalance(balance.subtract(amount));

        }

        walletRepository.save(wallet);
    }

    private void removeFromBalance(Operation operation, Category category, Wallet wallet) {
        BigDecimal amount = operation.getAmount();
        wallet.setBalance(wallet.getBalance().subtract(amount));

        if (category.getType() == CategoryType.INCOME) {
            wallet.setIncome(wallet.getIncome().subtract(amount));
        } else if (category.getType() == CategoryType.EXPENSE) {
            wallet.setExpense(wallet.getExpense().subtract(amount));
        }

        walletRepository.save(wallet);
    }
}
