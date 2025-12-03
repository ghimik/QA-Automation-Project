package com.qa.project.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.qa.project.pages.MainPage.openMainPage;

public class SearchTest extends BaseTest {

    private final String SEARCH_PROMPT = "JavaScript";

    /**
     * Выполняется открытие основной страницы, переход на страницу Book Application,
     * ввод поиска с заданным промптом, проверка что title каждой страницы содержит указанный промпт
     */
    @Test
    public void checkIncorrectRegistrationPassword() {
        openMainPage()
                .clickOnBookStoreApplicationButton()
                .searchBooks(SEARCH_PROMPT)
                .forEach(book -> Assertions.assertTrue(book.getTitle().contains(SEARCH_PROMPT)));
    }
}
