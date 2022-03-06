package com.github.divanchess.atm.controller;

import com.github.divanchess.atm.service.CardInfoService;
import com.github.divanchess.atm_dto.BalanceDto;
import com.github.divanchess.messages.Response;

public class BalanceController {
    //"/atm/balance/{pan}"
    public Response getBalanceByPan() {
        //Response.addResult(new CardInfoService.getBalanceFromServer());
        return new Response();
    }
}
