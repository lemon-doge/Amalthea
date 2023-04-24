package ru.hse.amaltheateam.wallets.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.amaltheateam.wallets.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
