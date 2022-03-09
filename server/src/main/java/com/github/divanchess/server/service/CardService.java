package com.github.divanchess.server.service;

import com.github.divanchess.dto.CardDto;
import com.github.divanchess.server.entity.Card;
import com.github.divanchess.exception.CardNotFoundException;
import com.github.divanchess.server.repository.CardRepository;
import com.github.divanchess.server.utils.MappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public CardDto getCardByPanAndPin(CardDto cardDto) {
        Card card = cardRepository.findByPanAndPin(cardDto.getPan(), cardDto.getPin()).orElseThrow(() -> new CardNotFoundException("Card not found"));
        return MappingUtils.mapCardEntityToCardDto(card);
    }

    public CardDto getCardByPan(CardDto cardDto) {
        Card card = cardRepository.findByPan(cardDto.getPan()).orElseThrow(() -> new CardNotFoundException("Card not found"));
        return MappingUtils.mapCardEntityToCardDto(card);
    }

}
