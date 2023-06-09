package ru.hse.amaltheateam.currencyrate.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllCurrencyRatesRequestDTO implements Serializable {

    @XmlElement(name = "Valute")
    List<CurrencyRateRequestDTO> currencyRateRequestDTOs;

    @XmlElement(name = "Date")
    private LocalDate date;

}
