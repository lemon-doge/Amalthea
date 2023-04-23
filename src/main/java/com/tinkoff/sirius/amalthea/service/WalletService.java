package com.tinkoff.sirius.amalthea.service;

import com.tinkoff.sirius.amalthea.dto.wallet.request.UpdateWalletRequestDTO;
import com.tinkoff.sirius.amalthea.dto.wallet.response.AllWalletsResponseDTO;
import com.tinkoff.sirius.amalthea.dto.wallet.request.CreateWalletRequestDTO;
import com.tinkoff.sirius.amalthea.dto.wallet.response.SingleWalletResponseDTO;
import com.tinkoff.sirius.amalthea.mapper.WalletMapper;
import com.tinkoff.sirius.amalthea.model.Wallet;
import com.tinkoff.sirius.amalthea.repository.CurrencyRepository;
import com.tinkoff.sirius.amalthea.repository.UserRepository;
import com.tinkoff.sirius.amalthea.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, WalletMapper walletMapper, CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public AllWalletsResponseDTO getAllWalletsByUserId(Long userId) {
        var wallets = walletRepository.findAllByUserId(userId);

        var totalBalance = wallets.stream()
                .map(Wallet::getBalance)
                .reduce(BigDecimal::add).orElseThrow();

        var totalIncome = wallets.stream()
                .map(Wallet::getIncome)
                .reduce(BigDecimal::add).orElseThrow();

        var totalExpense = wallets.stream()
                .map(Wallet::getExpense)
                .reduce(BigDecimal::add).orElseThrow();

        return AllWalletsResponseDTO.builder().wallets(wallets.stream().map(walletMapper::toDto).toList())
                .totalBalance(totalBalance).totalIncome(totalIncome).totalExpense(totalExpense).build();
    }

    @Transactional(readOnly = true)
    public SingleWalletResponseDTO getWalletById(Long walletId) {
        return walletMapper.toDto(walletRepository.findById(walletId).orElseThrow());
    }

    @Transactional
    public SingleWalletResponseDTO createWallet(Long userId, CreateWalletRequestDTO walletInfo) {
        var currency = currencyRepository.findById(walletInfo.getCurrencyId()).orElseThrow();
        var user = userRepository.findById(userId).orElseThrow();
        var wallet = Wallet.builder()
                .name(walletInfo.getName())
                .currency(currency)
                .limit(new BigDecimal(0))
                .balance(new BigDecimal(0))
                .income(new BigDecimal(0))
                .expense(new BigDecimal(0))
                .user(user)
                .build();
        return walletMapper.toDto(walletRepository.save(wallet));
    }

    @Transactional
    public void deleteWallet(Long walletId) {
        walletRepository.deleteById(walletId);
    }

    @Transactional
    public SingleWalletResponseDTO updateWallet(Long id, UpdateWalletRequestDTO updateWalletRequestDTO) {
        var old = walletRepository.findById(id).orElseThrow();
        old.setName(updateWalletRequestDTO.getName())
                .setLimit(updateWalletRequestDTO.getLimit());
        return walletMapper.toDto(walletRepository.save(old));
    }
}
