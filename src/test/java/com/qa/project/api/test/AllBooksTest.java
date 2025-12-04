package com.qa.project.api.test;

import com.qa.project.api.model.AllBooks;
import com.qa.project.api.model.Book;
import com.qa.project.api.specs.BooksSpecifications;
import com.qa.project.common.config.Properties;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.qa.project.api.service.BookApi.getAllBooks;
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
                .spec(BooksSpecifications.successResponseSpecification());

    }

    @ParameterizedTest
    @ValueSource(strings = {"9781491904244", "9781449337711"})
    public void testAtLeastOneBookGotByISBN(String isbn) {
        given()
                .spec(BooksSpecifications.defaultRequestSpecification())
                .param("ISBN", isbn)
                .when()
                .get("BookStore/v1/Book/")
                .then()
                .spec(BooksSpecifications.successResponseSpecification())
                .body(not(empty()));
    }

    @Test
    public void testNoBooksGotByInvalidISBN() {
        given().spec(BooksSpecifications.defaultRequestSpecification())
                .param("ISBN", "-1")
                .when()
                .get("BookStore/v1/Book/")
                .then()
                .spec(BooksSpecifications.badRequestResponseSpecification());
    }

    @Test
    public void testAllBooksHaveISBN() {
        getAllBooks()
                .forEach(book -> assertNotNull(book.getIsbn()));
    }


}
