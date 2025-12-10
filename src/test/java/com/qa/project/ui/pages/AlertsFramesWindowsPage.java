package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.components.AlertsComponent;
import com.qa.project.ui.components.FramesComponent;
import com.qa.project.ui.components.ModalDialogsComponent;

import static com.codeborne.selenide.Selenide.$x;

public class AlertsFramesWindowsPage {

    private final SelenideElement alertsButton = $x("//span[text()='Alerts']");

    private final SelenideElement nestedFramesButton = $x("//span[text()='Nested Frames']");

    private final SelenideElement modalDialogsButton = $x("//span[text()='Modal Dialogs']");


    public static AlertsFramesWindowsPage openAlertsFramesWindowsPage() {
        Selenide.open("/alertsWindows");
        return new AlertsFramesWindowsPage();
    }

    public AlertsComponent clickOnAlertsButton() {
        alertsButton.click();
        return new AlertsComponent();
    }

    public FramesComponent clickOnNestedFramesButton() {
        nestedFramesButton.click();
        return new FramesComponent();
    }

    public ModalDialogsComponent clickOnModalDialogsButton() {
        modalDialogsButton.click();
        return new ModalDialogsComponent();
    }

}
