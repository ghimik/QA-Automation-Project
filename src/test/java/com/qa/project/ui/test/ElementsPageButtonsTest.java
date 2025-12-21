package com.qa.project.ui.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Тестирование раздела Elements")
@Feature("Buttons")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/buttons")
@Severity(SeverityLevel.CRITICAL)
@Tag("ui")
@Tag("e2e")
public class ElementsPageButtonsTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Кнопка Double Click")
    @Description("Проверка, что после double click отображается правильное сообщение")
    void shouldShowMessageAfterDoubleClick() {
        final String message = openElementsPage()
                .clickOnButtonsButton()
                .doubleClick()
                .doubleClickParagraphText();

        assertNotNull(message, "Сообщение после double click не должно быть null");
        assertThat("Текст сообщения должен содержать 'You have done a double click'",
                message, containsString("You have done a double click"));
    }

    @Test
    @Story("Кнопка Right Click")
    @Description("Проверка, что после right click отображается правильное сообщение")
    void shouldShowMessageAfterRightClick() {
        final String message = openElementsPage()
                .clickOnButtonsButton()
                .rightClick()
                .rightClickParagraphText();

        assertNotNull(message, "Сообщение после right click не должно быть null");
        assertThat("Текст сообщения должен содержать 'You have done a right click'",
                message, containsString("You have done a right click"));
    }

    @Test
    @Story("Кнопка Обычный Click")
    @Description("Проверка, что после обычного клика отображается правильное сообщение")
    void shouldShowMessageAfterDefaultClick() {
        final String message = openElementsPage()
                .clickOnButtonsButton()
                .defaultClick()
                .dynamicClickParagraphText();

        assertNotNull(message, "Сообщение после обычного клика не должно быть null");
        assertThat("Текст сообщения должен содержать 'You have done a dynamic click'",
                message, containsString("You have done a dynamic click"));
    }
}