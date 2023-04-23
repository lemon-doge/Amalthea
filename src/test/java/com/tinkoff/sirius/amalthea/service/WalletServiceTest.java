package com.tinkoff.sirius.amalthea.service;

import com.tinkoff.sirius.amalthea.repository.WalletRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//class WalletServiceTest {
//
//    @MockBean
//    private WalletRepository walletRepository;
//
//    @Test
//    @Disabled
//    void getAllWallets() {
//        Mockito.when(walletRepository.count()).thenReturn(123L);
//        System.out.println(walletRepository.count());
//    }
//
//    @Test
//    void getWalletById() {
//    }
//
//    @Test
//    void createWallet() {
//    }
//
//    @Test
//    void deleteWallet() {
//    }
//
//    @Test
//    void updateWallet() {
//    }
//}