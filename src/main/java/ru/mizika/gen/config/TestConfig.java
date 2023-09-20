package ru.mizika.gen.config;


import ru.mizika.gen.petstore.api.PetstorePetApi;
import ru.mizika.gen.petstore.api.PetstoreStoreApi;
import ru.mizika.gen.petstore.api.PetstoreUserApi;


import static ru.mizika.gen.utils.Clients.getSpecBuilder;


public class TestConfig {

    public PetstorePetApi petstorePetApi() {
        return PetstorePetApi.pet(() -> getSpecBuilder(System.getProperty("petstore.productApi")));
    }

    public PetstoreStoreApi petstoreStoreApi() {
        return PetstoreStoreApi.store(() -> getSpecBuilder(System.getProperty("petstore.productApi")));
    }

    public PetstoreUserApi petstoreUserApi() {
        return PetstoreUserApi.user(() -> getSpecBuilder(System.getProperty("petstore.productApi")));
    }

}
