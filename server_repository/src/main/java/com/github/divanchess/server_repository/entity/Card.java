package com.github.divanchess.server_repository.entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private Long pan;
    private int pin;
    private Account account;
}
