package com.qa.project.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * Main page of demoqa.com site
 */
public class MainPage {

    private static final String URL = "https://demoqa.com/";

    private final SelenideElement bookStoreApplicationButton = $(By.xpath("//div[h5[text()='Book Store Application']]"));
    private final SelenideElement elementsButton = $(By.xpath("//div[h5[contains(text(), 'Elements')]]"));
    private final SelenideElement formsButton = $x("//div[contains(@class,'card-body') and h5[text()='Forms']]");
    private final SelenideElement alertsFramesWindowsButton = $(By.xpath("//div[h5[text()='Alerts, Frame & Windows']]"));
    private final SelenideElement widgetsButton = $(By.xpath("//div[h5[text()='InteractionsWidgets']]"));
    private final SelenideElement interactionsButton = $(By.xpath("//div[h5[text()='Interactions']]"));

    public BookStoreApplicationPage clickOnBookStoreApplicationButton() {
        bookStoreApplicationButton.scrollIntoView(true).click();
        return new BookStoreApplicationPage();
    }

    public static MainPage openMainPage() {
        Selenide.open(URL);
        return new MainPage();
    }

}
