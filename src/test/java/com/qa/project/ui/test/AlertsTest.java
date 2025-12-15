package com.qa.project.ui.test;

import com.codeborne.selenide.ex.AlertNotFoundError;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.qa.project.ui.pages.AlertsFramesWindowsPage.openAlertsFramesWindowsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Epic("Тестирование раздела Alerts (demoqa.com)")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/alerts")
@Severity(SeverityLevel.CRITICAL)
public class AlertsTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Простой алерт возможно принять")
    void testSimpleAlertAppears() {

        assertDoesNotThrow(() -> openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickAlertButton()
                .accept());
    }


    @Test
    @Story("Алерт с таймером на 5 секунд отображается через 6")
    void testTimerAlertAfter5Seconds() {
        assertDoesNotThrow(() -> openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickTimerAlertButton()
                .waitForAlert(6000)
                .getText()
        );
    }

    @Test
    @Story("Алерт с таймером через 5 секунд не доступен через 3 секунды")
    void testAlertNotAppearingAfter3Seconds() {
        assertThrows(AlertNotFoundError.class,
                () -> openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickTimerAlertButton()
                .waitForAlert(3000)
                .getText()
        );

    }

    @Test
    @Story("Алерт с подтверждением при подтверждении корректно обрабатывается")
    void testConfirmButtonShowsText() {
        final String confirmText = openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickConfirmButton()
                .accept()
                .getText();

        Allure.step("Проверить, что итоговый текст соответсвует заданному при подтверждении алерта",
                () -> assertThat(confirmText, Matchers.containsString("You selected Ok")));

    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "Кириллический промпт", "✔️эмодзи"})
    @Story("Выходной span содержит промпт из PromptBox")
    void testPromptTextMatches(String inputText) {
        final String outputText = openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickPromptButton()
                .sendPrompt(inputText)
                .getText();

        Allure.step("Проверить, что выходной текст содержит текст входного промпта",
                () -> assertThat(outputText, Matchers.containsString("You entered " + inputText)));

    }


}
