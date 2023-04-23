package com.tinkoff.sirius.amalthea.repository;

import com.tinkoff.sirius.amalthea.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
