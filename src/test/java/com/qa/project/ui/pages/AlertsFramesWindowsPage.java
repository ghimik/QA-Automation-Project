package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.components.AlertsComponent;
import com.qa.project.ui.components.FramesComponent;
import com.qa.project.ui.components.ModalDialogsComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AlertsFramesWindowsPage {

    private final SelenideElement alertsButton = $x("//span[text()='Alerts']");

    private final SelenideElement nestedFramesButton = $x("//span[text()='Nested Frames']");

    private final SelenideElement modalDialogsButton = $x("//span[text()='Modal Dialogs']");


    @Step("Открыть страницу 'Alerts, Frames & Windows' по URL")
    public static AlertsFramesWindowsPage openAlertsFramesWindowsPage() {
        Selenide.open("/alertsWindows");
        return new AlertsFramesWindowsPage();
    }

    @Step("Кликнуть на кнопку 'Alerts' в меню")
    public AlertsComponent clickOnAlertsButton() {
        alertsButton.click();
        return new AlertsComponent();
    }

    @Step("Кликнуть на кнопку 'Nested Frames' в меню")
    public FramesComponent clickOnNestedFramesButton() {
        nestedFramesButton.click();
        return new FramesComponent();
    }

    @Step("Кликнуть на кнопку 'Modal Dialogs' в меню")
    public ModalDialogsComponent clickOnModalDialogsButton() {
        modalDialogsButton.click();
        return new ModalDialogsComponent();
    }

}
