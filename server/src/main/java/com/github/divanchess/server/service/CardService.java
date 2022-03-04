package com.github.divanchess.server.service;

import com.github.divanchess.server_repository.repository.CardRepository;

public class CardService {
    public CardRepository getCardByPan(){return new CardRepository();}
}
