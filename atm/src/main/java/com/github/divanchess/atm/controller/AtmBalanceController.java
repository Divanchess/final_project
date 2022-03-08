package com.github.divanchess.atm.controller;

import com.github.divanchess.atm.service.CardInfoService;
import com.github.divanchess.dto.BalanceDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class AtmBalanceController {
    private CardInfoService cardInfoService;

    @GetMapping("/atm/balance")
    public ResponseEntity<BalanceDto> getBalanceByCardAndPin(@RequestParam("card") String card,
                                                             @RequestParam("pin") int pin) {
        return Optional.ofNullable(cardInfoService.getCardBalance(card, pin))
                //get card balance from server
                .map(balance -> new ResponseEntity<>(balance, HttpStatus.OK))
                //map it to HTTP OK Response and DTO object
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                //or return 500 http error
    }
}
