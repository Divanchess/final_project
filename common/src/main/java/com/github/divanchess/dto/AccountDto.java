package com.github.divanchess.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountDto {
    private Long accountId;
    private String number;
    private BalanceDto balance;
}
