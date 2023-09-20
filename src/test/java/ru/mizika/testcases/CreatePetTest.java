package ru.mizika.testcases;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mizika.gen.petstore.model.Category;
import ru.mizika.gen.petstore.model.Pet;
import ru.mizika.gen.petstore.model.Tag;
import ru.mizika.utils.BaseTest;
import ru.mizika.gen.config.TestConfig;

import java.util.List;
import java.util.Random;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static ru.mizika.gen.utils.ResponseSpecBuilders.shouldBeCode;
import static ru.mizika.gen.utils.ResponseSpecBuilders.validatedWith;

@DisplayName("Test")
@Feature("OpenApi generator example")
@Severity(SeverityLevel.NORMAL)
class CreatePetTest extends BaseTest {

    TestConfig testConfig = new TestConfig();
    Faker faker = new Faker();

    @Test
    @Story("Example")
    @DisplayName("Добавление нового питомца")
    void createPetTest() {
        step("Добавление питомца");
        var addNewPet = testConfig.petstorePetApi()
                .addPet()
                .body(new Pet()
                        .id(new Random().longs(10, 1000).findFirst().getAsLong())
                        .name(faker.name().firstName())
                        .category(new Category()
                                .id(new Random().longs(10, 1000).findFirst().getAsLong())
                                .name(faker.animal().name()))
                                .photoUrls(List.of(faker.internet().url()))
                                .tags(List.of(new Tag()
                                        .id(new Random().longs(10, 1000).findFirst().getAsLong())
                                        .name(faker.name().firstName())))
                                .status(Pet.StatusEnum.AVAILABLE)
                        ).executeAs(validatedWith(shouldBeCode(SC_OK)));
        assertAll(
                () -> assertThat("Проверка id у добавленного питомца",
                        addNewPet.getId(), notNullValue()),
                () -> assertThat("Проверка status у добавленного питомца",
                        addNewPet.getStatus(), equalTo(Pet.StatusEnum.AVAILABLE))
        );
    }
}
