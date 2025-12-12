package com.qa.project.api.reqresin.endpoints;

import com.qa.project.api.reqresin.model.ReqresinLoginRequest;
import com.qa.project.api.reqresin.model.ReqresinLoginResponse;
import com.qa.project.api.reqresin.model.ReqresinUserResponse;
import com.qa.project.api.reqresin.model.ReqresinUsersResponse;
import com.qa.project.api.reqresin.spec.RequestSpecifications;

import static io.restassured.RestAssured.given;

public class UserActionsEndpoints {

    public static ReqresinUsersResponse getUsers(int page) {
        return given()
                .spec(RequestSpecifications.baseSpecification())
                .param("page", page)
                .when()
                .get("/users")
                .then()
                .extract()
                .as(ReqresinUsersResponse.class);
    }

    public static ReqresinUserResponse getUserById(int id) {
        return given()
                .spec(RequestSpecifications.baseSpecification())
                .pathParam("id", id)
                .when()
                .get("/user")
                .thenReturn()
                .as(ReqresinUserResponse.class);
    }

    public static ReqresinLoginResponse login(ReqresinLoginRequest loginData) {
        return given()
                .spec(RequestSpecifications.baseSpecification())
                .when()
                .post("/login")
                .thenReturn()
                .as(ReqresinLoginResponse.class);
    }

}
