package com.github.divanchess.atm.controller;

import com.github.divanchess.atm.service.CardInfoService;
import com.github.divanchess.dto.AccountDto;
import com.github.divanchess.dto.BalanceDto;
import com.github.divanchess.exception.CardNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class AtmBalanceController {
    private CardInfoService cardInfoService;

    @GetMapping("/atm/card/balance")
    public ResponseEntity<BalanceDto> getBalanceByCardAndPin(@RequestParam("card") String card,
                                                             @RequestParam("pin") int pin) {
        return Optional.ofNullable(cardInfoService.getCardBalance(card, pin))
                .map(balance -> new ResponseEntity<>(balance, HttpStatus.OK))
                .orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @GetMapping("/atm/card/check")
    public ResponseEntity<AccountDto> checkCardAccountExists(@RequestParam("card") String card) {
        return Optional.ofNullable(cardInfoService.checkCardAccountExists(card))
                .map(accountDto -> new ResponseEntity<>(accountDto, HttpStatus.OK))
                .orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @GetMapping("/atm/index")
    public @ResponseBody String getGreetingAtm(){ return "Atm index"; }
}
