package com.qa.project.api.bookstore.test;

import com.qa.project.api.bookstore.specs.BooksSpecifications;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;

import static com.qa.project.api.bookstore.service.BookApi.getAllBooks;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

public class AllBooksTest {

    private final Logger LOGGER = Logger.getGlobal();


    @Test
    public void testAllBooksGot() {
        given()
                .spec(BooksSpecifications.defaultRequestSpecification())
                .when()
                .get("BookStore/v1/Books/")
                .then()
                .spec(com.qa.project.api.bookstore.specs.BooksSpecifications.successResponseSpecification());

    }

    @ParameterizedTest
    @ValueSource(strings = {"9781491904244", "9781449337711"})
    public void testAtLeastOneBookGotByISBN(String isbn) {
        given()
                .spec(com.qa.project.api.bookstore.specs.BooksSpecifications.defaultRequestSpecification())
                .param("ISBN", isbn)
                .when()
                .get("BookStore/v1/Book/")
                .then()
                .spec(com.qa.project.api.bookstore.specs.BooksSpecifications.successResponseSpecification())
                .body(not(empty()));
    }

    @Test
    public void testNoBooksGotByInvalidISBN() {
        given().spec(com.qa.project.api.bookstore.specs.BooksSpecifications.defaultRequestSpecification())
                .param("ISBN", "-1")
                .when()
                .get("BookStore/v1/Book/")
                .then()
                .spec(com.qa.project.api.bookstore.specs.BooksSpecifications.badRequestResponseSpecification());
    }

    @Test
    public void testAllBooksHaveISBN() {
        getAllBooks()
                .forEach(book -> assertNotNull(book.getIsbn()));
    }


}
