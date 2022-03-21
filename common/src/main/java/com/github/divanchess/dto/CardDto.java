package com.github.divanchess.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class CardDto {
    @NonNull
    private String pan;
    private int pin;
    private List<AccountDto> accountDtoList;
}