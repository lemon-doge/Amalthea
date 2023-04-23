package com.tinkoff.sirius.amalthea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "currency_rate")
public class CurrencyRate {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "rate")
    private BigDecimal rate;

    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
