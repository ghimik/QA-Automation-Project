package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ElementsPageButtonsComponent {
    private final SelenideElement doubleClickButton = $("#doubleClickBtn");
    private final SelenideElement rightClickButton = $("#rightClickBtn");
    private final SelenideElement clickButton = $x("//button[text()='Click Me']");

    private final SelenideElement dynamicClickParagraph = $("#dynamicClickMessage");
    private final SelenideElement rightClickParagraph = $("#rightClickMessage");
    private final SelenideElement doubleClickParagraph = $("#doubleClickMessage");

    @Step("Сделать double click на кнопке")
    public ElementsPageButtonsComponent doubleClick() {
        doubleClickButton.doubleClick();
        return this;
    }

    @Step("Сделать клик ПКМ на кнопке")
    public ElementsPageButtonsComponent rightClick() {
        actions().contextClick(rightClickButton).perform();
        return this;
    }

    @Step("Выполнить клик на кнопке 'Click Me'")
    public ElementsPageButtonsComponent defaultClick() {
        clickButton.click();
        return this;
    }

    public String dynamicClickParagraphText() {
        return dynamicClickParagraph.exists() ? dynamicClickParagraph.shouldBe(visible).getText() : null;
    }

    public String rightClickParagraphText() {
        return rightClickParagraph.exists() ?  rightClickParagraph.shouldBe(visible).getText() : null;
    }

    public String doubleClickParagraphText() {
        return doubleClickParagraph.exists() ? doubleClickParagraph.shouldBe(visible).getText() : null;
    }
}