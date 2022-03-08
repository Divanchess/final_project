package com.github.divanchess.server.controller;

import com.github.divanchess.dto.BalanceDto;
import com.github.divanchess.dto.CardDto;
import com.github.divanchess.server.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class BalanceController {
    private CardService cardService;

    @PostMapping("/server/atm/balance")
    public BalanceDto getBalanceByCard(@RequestBody CardDto cardDto) {
        BalanceDto balance = cardService.getCardInfo(cardDto.getPan()).getAccountDtoList().get(0).getBalance();
        log.info(balance.toString());
        return balance;
    }
}
