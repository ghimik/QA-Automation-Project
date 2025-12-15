package com.qa.project.api.reqresin.test;

import com.qa.project.api.reqresin.endpoints.UserActionsEndpoints;
import com.qa.project.api.reqresin.model.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.qa.project.api.reqresin.endpoints.RandomDataEndpoints.getUnknownData;
import static com.qa.project.api.reqresin.endpoints.UserActionsEndpoints.*;
import static com.qa.project.api.reqresin.spec.RequestSpecifications.baseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Тестирование API reqres.in")
@Owner("alexey")
@Link(name = "Документация API", url = "https://reqres.in/")
@Tag("api")
public class ReqresinTests {

    @Test
    @Story("Получение списка пользователей")
    @Description("GET /api/users?page={page} возвращает корректные данные: пагинация, тоталы")
    @Severity(SeverityLevel.BLOCKER)
    public void getUsersShouldReturnValidPageData() {
        int page = 1;
        Allure.parameter("Номер проверяемой", page);

        AtomicReference<ReqresinUsersResponse> response = new AtomicReference<>();
        Allure.step("Получить всех пользователей на странице {page}", () -> {
            response.set(getUsers(page));
        });


        Allure.step("Проверить номер страницы", () ->
                assertEquals(page, response.get().getPage(), "Номер страницы не совпадает")
        );

        Allure.step("Проверить непустоту списка пользователей", () ->
                assertFalse(response.get().getData().isEmpty(), "Список пользователей пустой")
        );

        Allure.step("Проверить количество элементов на странице", () ->
                assertTrue(response.get().getPer_page() > 0, "Количество элементов на странице " +
                        "должно быть больше нуля")
        );

    }

    @Test
    @Story("Получение пользователя по ID")
    @Description("GET /api/users/{id} возвращает корректные пользовательские данные")
    @Severity(SeverityLevel.BLOCKER)
    public void getUserByIdShouldReturnUser() {
        int userId = 2;
        Allure.parameter("User ID", userId);

        ReqresinUserResponse response = getUserById(userId);
        UserModel user = response.getData();

        Allure.step("Проверить, что ID пользователя не null", () ->
                assertNotNull(user, "ID пользователя null")
        );

        Allure.step("Проверить ID пользователя", () ->
                assertEquals(userId, user.getId(), "ID пользователя не совпадает с проверяемым")
        );

        Allure.step("Проверить email пользователя", () ->
                assertTrue(user.getEmail().contains("@"), "Email не содержит @")
        );

    }

    @Test
    @Story("Авторизация пользователя")
    @Description("POST /api/login возвращает какой-либо токен при корректных данных")
    @Severity(SeverityLevel.BLOCKER)
    public void loginShouldReturnToken() {
        ReqresinLoginRequest request = new ReqresinLoginRequest();
        request.setEmail("janet.weaver@reqres.in");
        request.setPassword("admin");

        Allure.addAttachment("Данные авторизации (по email+password)", "application/json",
                String.format("email: %s, password: %s", request.getEmail(), request.getPassword())
        );

        ReqresinLoginResponse response = login(request);
        String token = response.getToken();

        Allure.step("Проверить, что токен существует", () ->
                assertNotNull(token, "Токен null")
        );

        Allure.addAttachment("Полученный токен", "text/plain", token);
    }

    @Test
    @Story("Получение случайных данных")
    @Description("GET /api/unknown возвращает список цветов с корректными данными")
    @Severity(SeverityLevel.NORMAL)
    public void getUnknownDataShouldReturnColorList() {
        ReqresinUnknownResponse response = getUnknownData();

        Allure.step("Проверить общее количество", () ->
                assertTrue(response.getTotal() > 0, "Общее количество должно быть больше 0")
        );

        Allure.step("Проверить, что список не пустой", () ->
                assertFalse(response.getData().isEmpty(), "Список цветов пустой")
        );

        UnknownDataModel first = response.getData().get(0);

        Allure.step("Проверить первый элемент списка", () -> {
            // assertTrue(first.getId() > 0, "ID должен быть больше нуля");
            assertNotNull(first.getColor(), "Цвет не должен быть null");
            assertNotNull(first.getName(), "Название не должно быть null");
        });

        String colorsList = response.getData().stream()
                .map(item -> String.format("%s: %s", item.getName(), item.getColor()))
                .collect(Collectors.joining("\n"));

        Allure.addAttachment("Список цветов", "text/plain", colorsList);
    }

    @Test
    @Story("Безуспешная авторизация")
    @Description("POST /api/login возвращает 400 при некорректном пароле")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithInvalidPasswordShouldFail() {
        ReqresinLoginRequest request = new ReqresinLoginRequest();
        request.setEmail("janet.weaver@reqres.in");

        Allure.addAttachment("Проверямые корректные данные данные", "application/json",
                String.format("email: %s, password: null", request.getEmail())
        );

        int statusCode = given()
                .spec(baseSpecification())
                .body(request)
                .when()
                .post("/login")
                .then()
                .extract().statusCode();

        Allure.step("Проверить HTTP код", () ->
                assertEquals(400, statusCode, "Ожидалось HTTP 400 для некорректного пароля")
        );

        Allure.addAttachment("Итоговый  статус код", "text/plain", String.valueOf(statusCode));
    }

    @Test
    @Story("Поиск несуществующего пользователя")
    @Description("GET /api/users/{id} возвращает null для несуществующего ID")
    @Severity(SeverityLevel.NORMAL)
    public void getUserByNonExistingIdShouldReturnNull() {
        int userId = 9999;
        Allure.parameter("Несуществующий ID", userId);

        ReqresinUserResponse response = getUserById(userId);

        Allure.step("Проверить, что пользователь не найден", () ->
                assertNull(response.getData(), "Для несуществующего пользователя должен возвращаться null")
        );

        Allure.addAttachment("Ответ сервера", "application/json",
                String.format("data: %s", response.getData())
        );
    }


    @Step("Получить пользователя по ID ({userId})")
    private ReqresinUserResponse getUserById(int userId) {
        return UserActionsEndpoints.getUserById(userId);
    }

    @Step("Выполнить вход")
    private ReqresinLoginResponse login(ReqresinLoginRequest request) {
        return UserActionsEndpoints.login(request);
    }
}