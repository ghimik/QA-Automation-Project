package com.qa.project.api.bookstore.specs;

import com.qa.project.common.config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public final class BooksSpecifications {

    private BooksSpecifications() {
        throw new IllegalAccessError("BooksSpecifications cannot be instantiated; Utility class");
    }

    public static RequestSpecification defaultRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.baseUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification successResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification badRequestResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
