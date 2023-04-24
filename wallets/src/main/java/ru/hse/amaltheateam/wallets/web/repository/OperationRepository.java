package ru.hse.amaltheateam.wallets.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.amaltheateam.wallets.model.Operation;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

//    @Query(value = "SELECT * FROM operation WHERE wallet_id = ?1", nativeQuery = true)
    List<Operation> findAllByWalletIdOrderByDateDesc(Long walletId);
}
