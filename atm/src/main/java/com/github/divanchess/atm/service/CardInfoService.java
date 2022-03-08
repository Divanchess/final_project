package com.github.divanchess.atm.service;

import com.github.divanchess.dto.BalanceDto;
import com.github.divanchess.dto.CardDto;
import exception.CardNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
@Slf4j
public class CardInfoService {
    private String serverUrl;
    private String serverLogin;
    private String serverPassword;

    public CardInfoService(@Value("${atm.server.url}") String serverUrl,
                           @Value("${atm.server.login}") String serverLogin,
                           @Value("${atm.server.password}") String serverPassword) {
        this.serverUrl = serverUrl;
        this.serverLogin = serverLogin;
        this.serverPassword = serverPassword;
    }

    public HttpHeaders credentialsHeaders() {
        String credentials = this.serverLogin + ":" + this.serverPassword;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + encodedCredentials);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    public BalanceDto getCardBalance(String card, int pin) {
        String getBalanceUrl = serverUrl + "/server/atm/balance";
        HttpHeaders headers = this.credentialsHeaders();
        HttpEntity<CardDto> request = new HttpEntity<>(new CardDto(card, pin), headers);
        log.info(request.toString());
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<BalanceDto> response = restTemplate.postForEntity(getBalanceUrl, request, BalanceDto.class);
            log.info(response.toString());
            return response.getBody();
        } catch (CardNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card Not Found", e);
        } catch (Exception e) {
            log.error("Exception: ", e);
            throw e;
        }
    }

}