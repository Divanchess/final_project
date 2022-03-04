package com.github.divanchess.server_repository.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private Long id;
    private String number;
    private int balance;
    private Card card;
}
