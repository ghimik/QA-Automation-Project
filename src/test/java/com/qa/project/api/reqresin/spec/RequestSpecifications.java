package com.qa.project.api.reqresin.spec;

import com.qa.project.common.config.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {

    public static RequestSpecification baseSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Properties.REQRESIN_BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key", Properties.REQRESIN_API_KEY)
                .build();
    }
}
