package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import com.qa.project.common.LoginData;
import com.qa.project.ui.pages.ProfilePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginFormComponent {

    private final SelenideElement userNameTextBox = $x("//input[@id='userName']");
    private final SelenideElement passwordTextBox = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//button[@id='login']");

    @Step("Заполнить форму логина данными")
    public LoginFormComponent fillFieldsWith(LoginData data) {
        userNameTextBox.setValue(data.getUsername());
        passwordTextBox.setValue(data.getPassword());
        return this;
    }

    @Step("Нажать кнопку Login")
    public ProfilePage login() {
        loginButton.click();
        return new ProfilePage();
    }
}
