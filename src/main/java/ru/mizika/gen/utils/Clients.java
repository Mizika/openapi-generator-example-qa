package ru.mizika.gen.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.util.UUID;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static ru.mizika.gen.utils.JacksonObjectMapper.jackson;

public class Clients {

    public static RequestSpecBuilder getSpecBuilder(String url) {
        RestAssuredConfig restAssuredConfig = config().objectMapperConfig(objectMapperConfig().defaultObjectMapper(jackson()));
        return new RequestSpecBuilder().log(LogDetail.ALL)
                .setConfig(restAssuredConfig)
                .addFilter(new AllureFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBaseUri(url);
    }
}
