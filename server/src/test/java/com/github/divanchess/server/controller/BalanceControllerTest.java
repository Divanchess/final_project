package com.github.divanchess.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.divanchess.dto.AccountDto;
import com.github.divanchess.dto.BalanceDto;
import com.github.divanchess.dto.CardDto;
import com.github.divanchess.exception.CardNotFoundException;
import com.github.divanchess.server.entity.Account;
import com.github.divanchess.server.entity.Card;
import com.github.divanchess.server.repository.CardRepository;
import com.github.divanchess.server.service.CardService;
import com.github.divanchess.server.utils.MappingUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceControllerTest {
    CardDto cardDto;
    AccountDto accountDto;
    BalanceDto balanceDto;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CardService service;

    @BeforeEach
    public void setData() {
        balanceDto = new BalanceDto(new BigDecimal(9876.00));
        accountDto = new AccountDto(1L, "1234567QWER", balanceDto);
        cardDto = new CardDto("12345678901");
        cardDto.setPin(1234);
        cardDto.setAccountDtoList(Arrays.asList(accountDto));
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = { "ATM" })
    public void getBalanceByCardSuccessTest() throws Exception {
        when(service.getCardByPanAndPin(cardDto)).thenReturn(cardDto);
        String json = this.mockMvc.perform(
                        post("/server/card/balance")
                                .content(new ObjectMapper().writeValueAsString(cardDto))
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(balanceDto.getAmount().toString())))
                .andReturn().getResponse().getContentAsString();
        BalanceDto balanceDtoResult = new ObjectMapper().readValue(json, BalanceDto.class);
        assertThat(balanceDto).isEqualTo(balanceDtoResult);
    }


    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = { "ATM" })
    public void testAuthAndGreetings() throws Exception {
        this.mockMvc.perform(
                        get("/server/index")
                                .with(httpBasic("testUser","testPassword")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Server index")));
    }

}