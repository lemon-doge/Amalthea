package com.tinkoff.sirius.amalthea.repository;

import com.tinkoff.sirius.amalthea.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

//    @Query(value = "SELECT * FROM operation WHERE wallet_id = ?1", nativeQuery = true)
    List<Operation> findAllByWalletIdOrderByDateDesc(Long walletId);
}
