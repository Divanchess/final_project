package com.github.divanchess.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class BalanceDto {
    @NonNull
    private BigDecimal amount;
}