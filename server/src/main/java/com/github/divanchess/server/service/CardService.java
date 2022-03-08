package com.github.divanchess.server.service;

import com.github.divanchess.dto.CardDto;
import com.github.divanchess.server.entity.Card;
import exception.CardNotFoundException;
import com.github.divanchess.server.repository.CardRepository;
import com.github.divanchess.server.utils.MappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public CardDto getCardInfo(String pan) {
        Card card = cardRepository.findByPan(pan).orElseThrow(CardNotFoundException::new);
        return MappingUtils.mapCardEntityToCardDto(card);
    }

}
