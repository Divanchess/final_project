package com.github.divanchess.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Dto {
    private Long accountId;
    private String number;
    private BalanceDto balance;
}
