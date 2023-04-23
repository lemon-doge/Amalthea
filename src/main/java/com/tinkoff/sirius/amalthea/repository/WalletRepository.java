package com.tinkoff.sirius.amalthea.repository;

import com.tinkoff.sirius.amalthea.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findAllByUserId(Long userId);
}
