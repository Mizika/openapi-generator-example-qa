package ru.mizika.testcases;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mizika.gen.config.TestConfig;
import ru.mizika.gen.petstore.model.Category;
import ru.mizika.gen.petstore.model.Pet;
import ru.mizika.gen.petstore.model.Tag;
import ru.mizika.utils.BaseTest;

import java.util.List;
import java.util.Random;

import static io.qameta.allure.Allure.step;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static ru.mizika.gen.utils.ResponseSpecBuilders.shouldBeCode;
import static ru.mizika.gen.utils.ResponseSpecBuilders.validatedWith;

@DisplayName("Test")
@Feature("OpenApi-generator example")
@Severity(SeverityLevel.NORMAL)
class CreateAndFindPetTest extends BaseTest {

    TestConfig testConfig = new TestConfig();
    Faker faker = new Faker();
    Pet addNewPet;

    @BeforeEach
    void setup() {
        step("Добавление питомца");
        addNewPet = testConfig.petstorePetApi()
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

    @AfterEach
    void tearDown() {
        step("Удалить добавленного питомца");
        testConfig.petstorePetApi()
                .deletePet()
                .petIdPath(addNewPet.getId())
                .execute(validatedWith(shouldBeCode(SC_OK)));

    }

    @Test
    @Story("Example")
    @DisplayName("Поиск добавленного питомца")
    void createAndFindPetTest() {
        step("Поиск добавленного питомца по id");
        var findAddPet = testConfig.petstorePetApi()
                .getPetById()
                .petIdPath(addNewPet.getId())
                .executeAs(validatedWith(shouldBeCode(SC_OK)));
        assertAll(
                () -> assertThat("Проверка id у найденного питомца",
                        findAddPet.getId(), equalTo(addNewPet.getId())),
                () -> assertThat("Проверка category у найденного питомца",
                        findAddPet.getCategory(), notNullValue()),
                () -> assertThat("Проверка category id у найденного питомца",
                        findAddPet.getCategory().getId(), equalTo(addNewPet.getCategory().getId())),
                () -> assertThat("Проверка category name у найденного питомца",
                        findAddPet.getCategory().getName(), equalTo(addNewPet.getCategory().getName())),
                () -> assertThat("Проверка name у найденного питомца",
                        findAddPet.getName(), equalTo(addNewPet.getName())),
                () -> assertThat("Проверка photoUrls у найденного питомца",
                        findAddPet.getPhotoUrls(), hasSize(1)),
                () -> assertThat("Проверка tags у найденного питомца",
                        findAddPet.getTags(), notNullValue()),
                () -> assertThat("Проверка tags id у найденного питомца",
                        findAddPet.getTags().get(0).getId(), equalTo(addNewPet.getTags().get(0).getId())),
                () -> assertThat("Проверка tags name у найденного питомца",
                        findAddPet.getTags().get(0).getName(), equalTo(addNewPet.getTags().get(0).getName())),
                () -> assertThat("Проверка status у найденного питомца",
                        findAddPet.getTags().get(0).getName(), equalTo(addNewPet.getTags().get(0).getName()))
        );
    }
}
