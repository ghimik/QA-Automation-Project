package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * Main page of demoqa.com site
 */
public class MainPage {


    private final SelenideElement bookStoreApplicationButton = $(By.xpath("//div[h5[text()='Book Store Application']]"));
    private final SelenideElement elementsButton = $(By.xpath("//div[h5[contains(text(), 'Elements')]]"));
    private final SelenideElement formsButton = $x("//div[contains(@class,'card-body') and h5[text()='Forms']]");
    private final SelenideElement alertsFramesWindowsButton = $(By.xpath("//div[h5[text()='Alerts, Frame & Windows']]"));
    private final SelenideElement widgetsButton = $(By.xpath("//div[h5[text()='InteractionsWidgets']]"));
    private final SelenideElement interactionsButton = $(By.xpath("//div[h5[text()='Interactions']]"));


    @Step("Кликнуть на карточку 'Book Store Application'")
    public BookStoreApplicationPage clickOnBookStoreApplicationButton() {
        bookStoreApplicationButton.scrollIntoView(true).click();
        return new BookStoreApplicationPage();
    }

    @Step("Открыть главную страницу по URL")
    public static MainPage openMainPage() {
        Selenide.open("/");
        return new MainPage();
    }

    @Step("Кликнуть на карточку 'Forms'")
    public FormsPage clickOnFormsButton() {
        formsButton.scrollIntoView(true).click();
        return new FormsPage();
    }

    @Step("Кликнуть на карточку 'Elements'")
    public ElementsPage clickOnElementsButton() {
        elementsButton.scrollIntoView(true).click();
        return new ElementsPage();
    }

    @Step("Кликнуть на карточку 'Alerts, Frame & Windows'")
    public AlertsFramesWindowsPage clickOnAlertsFramesWindowsButton() {
        alertsFramesWindowsButton.scrollIntoView(true).click();
        return new AlertsFramesWindowsPage();
    }

    @Step("Кликнуть на карточку 'Interactions'")
    public InteractionsPage clickOnInteractionsButton() {
        interactionsButton.scrollIntoView(true).click();
        return new InteractionsPage();
    }

}
