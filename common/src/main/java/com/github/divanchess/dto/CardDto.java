package com.github.divanchess.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class CardDto implements Dto {
    @NonNull
    private String pan;
    @NonNull
    private int pin;
    private List<AccountDto> accountDtoList;
}