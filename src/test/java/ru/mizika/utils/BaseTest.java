package ru.mizika.utils;


import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseTest {

    @Step("{info}")
    public void step(String info) {
        log.info("======================");
        log.info(info);
        log.info("======================");
    }
}
