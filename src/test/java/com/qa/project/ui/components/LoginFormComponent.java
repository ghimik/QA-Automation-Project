package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import com.qa.project.common.LoginData;
import com.qa.project.ui.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginFormComponent {

    private final SelenideElement userNameTextBox = $x("//input[@id='userName']");
    private final SelenideElement passwordTextBox = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//button[@id='login']");

    public LoginFormComponent fillFieldsWith(LoginData data) {
        userNameTextBox.setValue(data.getUsername());
        passwordTextBox.setValue(data.getPassword());
        return this;
    }

    public ProfilePage login() {
        loginButton.click();
        return new ProfilePage();
    }
}
