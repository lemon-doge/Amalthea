package com.tinkoff.sirius.amalthea.mapper;

import com.tinkoff.sirius.amalthea.dto.wallet.response.SingleWalletResponseDTO;
import com.tinkoff.sirius.amalthea.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

// todo: сделать маппер SingleWalletDTO <-> Wallet
// todo: сделать нормально https://habr.com/ru/post/438808/

@Component
public class WalletMapper {
    private final CurrencyMapper currencyMapper;

    @Autowired
    public WalletMapper(CurrencyMapper currencyMapper) {
        this.currencyMapper = currencyMapper;
    }

//    public Wallet toEntity(CreateWalletRequestDTO dto) {
//        return Wallet.builder()
//                .balance(new BigDecimal(0))
//                .expense(new BigDecimal(0))
//                .income(new BigDecimal(0))
//                .currencyId(dto.getCurrencyId())
//                .limit(dto.getLimit())
//                .name(dto.getName()).build();
//    }
//
//    @Validated
//    public Wallet toEntity(UpdateWalletRequestDTO dto) {
//        return Wallet.builder()
//                .id(dto.getId())
//                .balance(dto.getBalance())
//                .expense(dto.getExpense())
//                .income(dto.getIncome())
//                .currencyId(dto.getCurrencyId())
//                .limit(dto.getLimit())
//                .name(dto.getName()).build();
//    }
//
//    @Validated
//    public Wallet toEntity(SingleWalletResponseDTO dto) {
//        return Wallet.builder()
//                .id(dto.getId())
//                .balance(dto.getBalance())
//                .name(dto.getName())
//                .expense(dto.getExpense())
//                .income(dto.getIncome())
//                .currencyId(dto.getCurrencyId())
//                .limit(dto.getLimit()).build();
//    }

    @Validated
    public SingleWalletResponseDTO toDto(Wallet entity) {
        return SingleWalletResponseDTO.builder()
                .id(entity.getId())
                .balance(entity.getBalance())
                .currency(currencyMapper.toResponseDTO(entity.getCurrency()))
                .expense(entity.getExpense())
                .income(entity.getIncome())
                .limit(entity.getLimit())
                .name(entity.getName())
                .build();
    }
}

