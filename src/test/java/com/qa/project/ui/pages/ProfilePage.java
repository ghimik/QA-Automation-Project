package com.qa.project.ui.pages;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private static final String goToBookStoreButton = "//button[@id='gotoStore']";

    public BookStoreApplicationPage clickOnGoToBookStoreButton() {
        $x(goToBookStoreButton).click();
        return new BookStoreApplicationPage();
    }

}
