package com.qa.project.api.reqresin.endpoints;

import com.qa.project.api.reqresin.model.ReqresinUnknownResponse;
import com.qa.project.api.reqresin.spec.RequestSpecifications;

import static io.restassured.RestAssured.given;

public class RandomDataEndpoints {

    public static ReqresinUnknownResponse getUnknownData() {
        return given()
                .spec(RequestSpecifications.baseSpecification())
                .when()
                .get("/unknown")
                .as(ReqresinUnknownResponse.class);
    }
}
