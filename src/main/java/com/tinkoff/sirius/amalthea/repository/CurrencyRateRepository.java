package com.tinkoff.sirius.amalthea.repository;

import com.tinkoff.sirius.amalthea.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long>  {
}
