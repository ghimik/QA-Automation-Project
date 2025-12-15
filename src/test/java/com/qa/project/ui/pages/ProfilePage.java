package com.qa.project.ui.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private static final String goToBookStoreButton = "//button[@id='gotoStore']";

    @Step("Кликнуть на кнопку 'Go To Book Store'")
    public BookStoreApplicationPage clickOnGoToBookStoreButton() {
        $x(goToBookStoreButton).click();
        return new BookStoreApplicationPage();
    }

}
