package com.qa.project.api.bookstore.service;

import com.qa.project.api.bookstore.model.Book;
import io.restassured.response.Response;

import java.util.List;
import java.util.Optional;

import static com.qa.project.api.bookstore.specs.BooksSpecifications.defaultRequestSpecification;
import static io.restassured.RestAssured.given;

public final class BookApi {

    public static List<Book> getAllBooks() {
        return
                given()
                        .spec(defaultRequestSpecification())
                        .when()
                        .get("BookStore/v1/Books/")
                        .then()
                        .extract()
                        .jsonPath()
                        .getList("books", Book.class);
    }

    public static Optional<Book> getBookByISBN(String isbn) {
        final Response response = given()
                            .spec(defaultRequestSpecification())
                            .param("ISBN", isbn)
                        .when()
                            .get("BookStore/v1/Books/");


        return switch (response.getStatusCode()) {
            case 200 -> Optional.ofNullable(response.getBody().as(Book.class));
            case 400 -> Optional.empty();
            default -> throw new RuntimeException("Something went wrong");
        };

    }


}
