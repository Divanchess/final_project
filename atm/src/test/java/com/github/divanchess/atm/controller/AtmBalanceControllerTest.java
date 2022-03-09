package com.github.divanchess.atm.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.divanchess.atm.service.CardInfoService;
import com.github.divanchess.dto.BalanceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AtmBalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CardInfoService service;

    @Test
    public void getBalanceByCardAndPinSuccessTest() throws Exception {
        String card = "10101010101";
        int pin = 1010;
        BalanceDto balanceDto = new BalanceDto(new BigDecimal(932.32));

        when(service.getCardBalance(card, pin)).thenReturn(balanceDto);
        String json = this.mockMvc.perform(
                        get("/atm/card/balance")
                                .param("card", card)
                                .param("pin", String.valueOf(pin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("932.32")))
                .andReturn().getResponse().getContentAsString();
        BalanceDto balanceDtoResult = new ObjectMapper().readValue(json, BalanceDto.class);
        assertThat(balanceDto).isEqualTo(balanceDtoResult);
    }

    @Test
    public void getBalanceByCardAndPinReturnNotFoundTest() throws Exception {
        this.mockMvc.perform(
                        get("/atm/card/balance")
                                .param("card", "111")
                                .param("pin", "111"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGreetings() throws Exception {
        this.mockMvc.perform(
                        get("/atm/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Atm index")));
    }

}