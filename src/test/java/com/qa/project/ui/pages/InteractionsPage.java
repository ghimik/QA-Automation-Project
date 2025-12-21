package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.components.DroppableInteractionsComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class InteractionsPage {

    private final SelenideElement droppableButton = $x("//span[text() = 'Droppable']");

    @Step("Открыть страницу Interactions по URL")
    public static InteractionsPage open() {
        Selenide.open("/interaction");
        return new InteractionsPage();
    }

    @Step("Кликнуть на меню 'Droppable'")
    public DroppableInteractionsComponent clickDroppableButton() {
        this.droppableButton.shouldBe(visible).click();
        return new DroppableInteractionsComponent();
    }
}