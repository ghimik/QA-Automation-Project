package com.qa.project.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.Alert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class AlertsComponent {

    private final SelenideElement root = $("#javascriptAlertsWrapper");

    private final SelenideElement alertButton = $("#alertButton");
    private final SelenideElement timerAlertButton = $("#timerAlertButton");
    private final SelenideElement confirmButton = $("#confirmButton");
    private final SelenideElement promptButton = $("#promtButton");

    public static class AlertActions {
        @Step("Принять алерт")
        public AlertActions accept() {
            Selenide.confirm();
            return this;
        }

        @Step("Отклонить алерт")
        public AlertActions dismiss() {
            Selenide.dismiss();
            return this;
        }

        @Step("Отправить промпт в PromtBox")
        public AlertActions sendPrompt(String text) {
            Selenide.prompt(text);
            return this;
        }

        /**
         * Можно было бы через Selenide.Wait с поллингом и проверкой на существование алерта сделать,
         * но так как отдельного ТЗ нет, то я делаю вывод, что сам факт ожидания в какое-то количество секунд
         * является частью ТЗ, а не предельным таймаутом ожидания ответа, например
         */
        public Alert waitForAlert(long timeoutMillis) {
            return Selenide.switchTo().alert(Duration.ofMillis(timeoutMillis));
        }


        public String getText() {
            return Selenide.switchTo().alert().getText();
        }
    }

    @RequiredArgsConstructor
    public static class AlertActionsWithResultingSpan extends AlertActions {
        private final SelenideElement locator;

        public String getText() { return locator.shouldBe(visible).getText(); }
    }

    @Step("Кликнуть на кнопку с простым алертом")
    public AlertActions clickAlertButton() {
        alertButton.click();
        return new AlertActions();
    }

    @Step("Кликнуть на кнопку с алертом с таймером")
    public AlertActions clickTimerAlertButton() {
        timerAlertButton.click();
        return new AlertActions();
    }

    @Step("Кликнуть на кнопку с ConfirmBox")
    public AlertActionsWithResultingSpan clickConfirmButton() {
        confirmButton.click();
        return new AlertActionsWithResultingSpan($x("//span[@id='confirmResult']"));
    }

    @Step("Кликнуть на кнопку вызывающую промпт")
    public AlertActionsWithResultingSpan clickPromptButton() {
        promptButton.click();
        return new AlertActionsWithResultingSpan($x("//span[@id='promptResult']"));
    }
}