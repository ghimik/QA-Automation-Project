package com.qa.project.api.reqresin.test;

import com.qa.project.api.reqresin.model.*;
import org.junit.jupiter.api.Test;

import static com.qa.project.api.reqresin.endpoints.RandomDataEndpoints.getUnknownData;
import static com.qa.project.api.reqresin.endpoints.UserActionsEndpoints.*;
import static com.qa.project.api.reqresin.spec.RequestSpecifications.baseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ReqresinTests {

    @Test
    public void getUsersShouldReturnValidPageData() {
        int page = 1;

        ReqresinUsersResponse response = getUsers(page);

        assertEquals(page, response.getPage(), "incorrect page number");
        assertFalse(response.getData().isEmpty(), "no users found");
        assertTrue(response.getPer_page() > 0, "per_page is less than zero");
    }

    @Test
    public void getUserByIdShouldReturnUser() {
        int userId = 2;

        ReqresinUserResponse response = getUserById(userId);

        assertNotNull(response.getData(), "user not found");
        assertEquals(userId, response.getData().getId(), "returned wrong id");
        assertTrue(response.getData().getEmail().contains("@"), "invalid email");
    }

    @Test
    public void loginShouldReturnToken() {
        ReqresinLoginRequest request = new ReqresinLoginRequest();
        request.setEmail("janet.weaver@reqres.in");
        request.setPassword("admin");

        assertNotNull(login(request).getToken(), "token is null");
    }

    @Test
    public void getUnknownDataShouldReturnColorList() {
        ReqresinUnknownResponse response = getUnknownData();

        assertTrue(response.getTotal() > 0, "total is zero");
        assertFalse(response.getData().isEmpty(), "data list is empty");

        UnknownDataModel first = response.getData().get(0);
        assertTrue(first.getId() > 0, "invalid id");
        assertNotNull(first.getColor(), "color is null");
    }

    @Test
    public void loginWithInvalidPasswordShouldFail() {
        ReqresinLoginRequest request = new ReqresinLoginRequest();
        request.setEmail("janet.weaver@reqres.in");

        int statusCode = given()
                .spec(baseSpecification())
                .body(request)
                .when()
                .post("/login")
                .thenReturn().statusCode();

        assertEquals(400, statusCode, "expected 400 for invalid password");
    }

    @Test
    public void getUserByNonExistingIdShouldReturnNull() {
        int userId = 9999;

        ReqresinUserResponse response = getUserById(userId);

        assertNull(response.getData(), "expected null for not-existing user");
    }
}
