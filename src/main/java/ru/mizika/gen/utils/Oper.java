package ru.mizika.gen.utils;

import groovy.transform.Generated;
import io.restassured.response.Response;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface Oper {

    @Generated
    <T> T execute(Function<Response, T> handler);

    default <T> T execToObj(int status, String path, Class<T> clazz) {
        Response response = execute(r -> {
            r.prettyPeek();
            r.then().statusCode(status);
            return r;
        });
        return Optional.ofNullable(response.jsonPath().getObject(path, clazz))
                .orElseThrow(() -> new RuntimeException(
                        "No object on given path: " + path +
                                "\nResponse body:\n" + response.getBody().prettyPrint()));
    }

    default String execToString(int status, String path) {
        Response response = execute(r -> {
            r.prettyPeek();
            r.then().statusCode(status);
            return r;
        });
        return Optional.ofNullable(response.jsonPath().getString(path))
                .orElseThrow(() -> new RuntimeException(
                        "No object on given path: " + path +
                                "\nResponse body:\n" + response.getBody().prettyPrint()));
    }

    default <T> List<T> execToList(int status, String path, Class<T> clazz) {
        Response response = execute(r -> {
            r.prettyPeek();
            r.then().statusCode(status);
            return r;
        });
        return Optional.ofNullable(response.jsonPath().getList(path, clazz))
                .orElseThrow(() -> new RuntimeException(
                        "No object on given path: " + path +
                                "\nResponse body:\n" + response.getBody().prettyPrint()));
    }


}