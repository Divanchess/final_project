package com.github.divanchess.atm;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.divanchess.atm.controller.AtmBalanceController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private AtmBalanceController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}