package com.github.divanchess.server.controller;

import com.github.divanchess.dto.AccountDto;
import com.github.divanchess.dto.BalanceDto;
import com.github.divanchess.dto.CardDto;
import com.github.divanchess.exception.CardNotFoundException;
import com.github.divanchess.server.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class BalanceController {
    private CardService cardService;

    @PostMapping("/server/card/balance")
    public ResponseEntity<BalanceDto> getBalanceByCard(@RequestBody CardDto cardDto) {
        return Optional.ofNullable(cardService.getCardByPanAndPin(cardDto).getAccountDtoList().get(0).getBalance())
        .map(balance -> new ResponseEntity<>(balance, HttpStatus.OK))
        .orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @PostMapping("/server/card/check")
    public ResponseEntity<AccountDto> checkCardAccountExists(@RequestBody CardDto cardDto) {
        return Optional.ofNullable(cardService.getCardByPan(cardDto).getAccountDtoList().get(0))
        .map(account -> new ResponseEntity<>(account, HttpStatus.OK))
        .orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @GetMapping("/server/index")
    public @ResponseBody String getGreetingServer(){
        return "Server index";
    }
}
