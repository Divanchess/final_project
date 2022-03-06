package com.github.divanchess.server_repository.repository;

import com.github.divanchess.server_repository.entity.Card;

import java.util.Set;

public class CardRepository {
    private Card card;
    private Set<AccountRepository> accounts;

    public Card getCardByPan() {return this.card;}
    public Set<AccountRepository> getAccounts() {return this.accounts;}
}
