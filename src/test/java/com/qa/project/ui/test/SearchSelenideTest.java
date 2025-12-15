package com.qa.project.ui.test;


import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static com.qa.project.ui.pages.MainPage.openMainPage;


@Epic("Тестирование Book Store Application")
@Feature("Поиск книг")
@Owner("alexey")
@Link(name = "Ссылка на Book Store", url = "https://demoqa.com/books")
@Severity(SeverityLevel.CRITICAL)
@Tag("ui")
@Tag("e2e")
public class SearchSelenideTest extends UnauthorizedSelenideTest {

    private static List<String> searchTermsProvider() {return List.of("JavaScript"); }

    @ParameterizedTest()
    @MethodSource("searchTermsProvider")
    @Story("Поиск книг по названию")
    @Description("Поиск книг по запросу {term} и проверка что все найденные книги содержат этот запрос в названии")
    public void checkIfAllBooksTitlesContainsSearchPrompt(String term) {
        openMainPage()
                .clickOnBookStoreApplicationButton()
                .searchBooks(term)
                .forEach(book -> Assertions.assertTrue(book.getTitle().contains(term)));
    }



}
