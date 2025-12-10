package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.components.DroppableInteractionsComponent;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class InteractionsPage {

    private final SelenideElement droppableButton = $x("//span[text() = 'Droppable']");

    public static InteractionsPage open() {
        Selenide.open("/interaction");
        return new InteractionsPage();
    }

    public DroppableInteractionsComponent clickDroppableButton() {
        this.droppableButton.shouldBe(visible).click();
        return new DroppableInteractionsComponent();
    }

}
