package ru.hse.amaltheateam.wallets.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.amaltheateam.wallets.dto.wallet.request.CreateWalletRequestDTO;
import ru.hse.amaltheateam.wallets.dto.wallet.request.UpdateWalletRequestDTO;
import ru.hse.amaltheateam.wallets.dto.wallet.response.AllWalletsResponseDTO;
import ru.hse.amaltheateam.wallets.dto.wallet.response.SingleWalletResponseDTO;
import ru.hse.amaltheateam.wallets.model.Wallet;
import ru.hse.amaltheateam.wallets.web.mappers.WalletMapper;
import ru.hse.amaltheateam.wallets.web.repository.CurrencyRepository;
import ru.hse.amaltheateam.wallets.web.repository.WalletRepository;
import ru.hse.amaltheateam.wallets.web.services.users.UsersService;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final CurrencyRepository currencyRepository;
    private final UsersService usersService;

    @Autowired
    public WalletService(WalletRepository walletRepository, WalletMapper walletMapper, CurrencyRepository currencyRepository, UsersService usersService) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
        this.currencyRepository = currencyRepository;
        this.usersService = usersService;
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
        var user = usersService.getUserById(userId).orElseThrow();
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
