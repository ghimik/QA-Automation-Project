package com.qa.project.api.bookstore.test;

import com.qa.project.api.bookstore.model.Book;
import com.qa.project.api.bookstore.service.BookApi;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

import static com.qa.project.api.bookstore.service.BookApi.getAllBooks;
import static com.qa.project.api.bookstore.specs.BooksSpecifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Тестирование API BookStore (demoqa.com)")
@Owner("alexey")
@Link(name = "Документация API", url = "https://demoqa.com/swagger/")
@Tag("api")
public class AllBooksTest {


    @Test
    @Feature("Все книги возможно получить")
    @Severity(SeverityLevel.BLOCKER)
    @Description("GET /BookStore/v1/Books должен вернуть непустой список книг")
    @Tag("regression")
    public void testAllBooksGot() {
        Assertions.assertDoesNotThrow(BookApi::getAllBooks);

    }

    @ParameterizedTest
    @ValueSource(strings = {"9781491904244", "9781449337711"})
    @Story("Получена как минимум одна книга по isbn ({isbn})")
    @Severity(SeverityLevel.CRITICAL)
    @Description("GET /BookStore/v1/Book?ISBN={isbn} возвращает одну книгу")
    @Tag("smoke")
    public void testAtLeastOneBookGotByISBN(String isbn) {

        Assertions.assertDoesNotThrow(() ->
            given()
                .spec(defaultRequestSpecification())
                .param("ISBN", isbn)
                .when()
                .get("BookStore/v1/Book/")
                .then()
                .spec(successResponseSpecification())
                .body(not(empty()))
                .and()
                .extract().as(Book.class)
        );
    }

    @Test
    @Story("Ни одна книга по некорректному ISBN не получена (сервер вернул Bad Request)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("GET /BookStore/v1/Book?ISBN={incorrectISBN} возвращает 400 Bad Request")
    @Tag("smoke")
    public void testNoBooksGotByInvalidISBN() {
        String incorrectISBN = "-1";
        Allure.parameter("Некорректный ISBN", incorrectISBN);

        given().spec(defaultRequestSpecification())
                .param("ISBN", incorrectISBN)
                .when()
                .get("BookStore/v1/Book/")
                .then()
                .spec(badRequestResponseSpecification());
    }

    @Test
    @Feature("Все книги имеют not null ISBN")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Все книги должны иметь непустой ISBN")
    @Tag("regression")
    public void testAllBooksHaveISBN() {
        List<Book> books = getAllBooks();
        List<Book> booksWithoutIsbn =
                books.stream()
                .filter(book -> book.getIsbn() == null || book.getIsbn().isEmpty())
                .toList();

        if (!booksWithoutIsbn.isEmpty()) {
            String booksWithoutIsbnTitles = booksWithoutIsbn.stream()
                    .map(Book::getTitle)
                    .collect(Collectors.joining(", "));

            Allure.addAttachment("Книги без ISBN", "text/plain", booksWithoutIsbnTitles);
        }

        assertTrue(booksWithoutIsbn.isEmpty(),
                "Количество книг без ISBN: " + booksWithoutIsbn.size());
    }


}
