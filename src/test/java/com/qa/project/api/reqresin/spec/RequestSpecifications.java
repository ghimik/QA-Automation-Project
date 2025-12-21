package com.qa.project.api.reqresin.spec;

import com.qa.project.common.config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {

    public static RequestSpecification baseSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.reqresinBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key", Config.reqresinApiKey())
                .build();
    }
}
