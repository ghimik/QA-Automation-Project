package com.qa.project.ui.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.MainPage.openMainPage;

public class SearchSelenideTest extends UnauthorizedSelenideTest {

    private final String SEARCH_PROMPT = "JavaScript";

    /**
     * Выполняется открытие основной страницы, переход на страницу Book Application,
     * ввод поиска с заданным промптом, проверка что title каждой страницы содержит указанный промпт
     */
    // @Test
    public void checkIfAllBooksTitlesContainsSearchPrompt() {
        openMainPage()
                .clickOnBookStoreApplicationButton()
                .searchBooks(SEARCH_PROMPT)
                .forEach(book -> Assertions.assertTrue(book.getTitle().contains(SEARCH_PROMPT)));
    }



}
