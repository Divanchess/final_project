package com.github.divanchess.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.divanchess.dto.AccountDto;
import com.github.divanchess.dto.BalanceDto;
import com.github.divanchess.dto.CardDto;
import com.github.divanchess.exception.CardNotFoundException;
import com.github.divanchess.server.entity.Account;
import com.github.divanchess.server.entity.Card;
import com.github.divanchess.server.repository.CardRepository;
import com.github.divanchess.server.utils.MappingUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class CardServiceTest {
    Card card;
    Account account;
    BigDecimal balance;

    @BeforeEach
    public void setData() {;
        balance = new BigDecimal(9861.32);
        account = new Account();
        account.setId(1L);
        account.setBalance(balance);
        account.setNumber("1234567QWER");
        card = new Card();
        card.setId(1L);
        card.setPan("12345678901");
        card.setPin(1234);
        card.setAccounts(Arrays.asList(account));
    }

    @Test
    public void getBalanceByCardSuccessTest() {
        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        when(cardRepository.findByPanAndPin(card.getPan(), card.getPin())).thenReturn(Optional.of(card));

        CardService cardService = new CardService(cardRepository);
        CardDto cardDtoRepository = MappingUtils.mapCardEntityToCardDto(card);
        CardDto cardDtoRequest = new CardDto("12345678901");
        cardDtoRequest.setPin(1234);

        assertThat(cardService.getCardByPanAndPin(cardDtoRequest)).isEqualTo(cardDtoRepository);
    }

    @Test
    public void getBalanceByCardNotFoundTest() {
        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        when(cardRepository.findByPanAndPin(card.getPan(), card.getPin())).thenThrow(new CardNotFoundException("Card not found"));

        CardService cardService = new CardService(cardRepository);
        CardDto cardDtoRepository = MappingUtils.mapCardEntityToCardDto(card);

        assertThrows(CardNotFoundException.class, () -> cardService.getCardByPanAndPin(cardDtoRepository));
    }
}