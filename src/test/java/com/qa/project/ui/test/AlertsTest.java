package com.qa.project.ui.test;

import com.codeborne.selenide.ex.AlertNotFoundError;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.NoAlertPresentException;

import static com.qa.project.ui.pages.AlertsFramesWindowsPage.openAlertsFramesWindowsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class AlertsTest extends UnauthorizedSelenideTest {

    @Test
    void testSimpleAlertAppears() {
        assertDoesNotThrow(() -> openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickAlertButton()
                .accept());
    }


    @Test
    void testTimerAlertAfter5Seconds() {
        assertDoesNotThrow(() -> openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickTimerAlertButton()
                .waitForAlert(6000)
                .getText()
        );
    }

    @Test
    void testAlertNotAppearingAfter3Seconds() {
        assertThrows(AlertNotFoundError.class, () -> openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickTimerAlertButton()
                .waitForAlert(3000)
                .getText()
        );

    }

    @Test
    void testConfirmButtonShowsText() {
        final String confirmText = openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickConfirmButton()
                .accept()
                .getText();

        assertThat(confirmText, Matchers.containsString("You selected Ok"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "Кириллический промпт", "✔️эмодзи"})
    void testPromptTextMatches(String inputText) {
        final String outputText = openAlertsFramesWindowsPage()
                .clickOnAlertsButton()
                .clickPromptButton()
                .sendPrompt(inputText)
                .getText();

        assertThat(outputText, Matchers.containsString("You entered " + inputText));
    }


}
