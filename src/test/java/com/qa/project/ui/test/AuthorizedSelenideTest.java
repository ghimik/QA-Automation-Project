package com.qa.project.ui.test;

import com.qa.project.ui.pages.ProfilePage;

import static com.qa.project.ui.pages.MainPage.openMainPage;
import static com.qa.project.common.util.LoginDataHelper.demoUserLoginDataFromProperties;

public abstract class AuthorizedSelenideTest extends BaseSelenideTest {

    @Override
    public boolean shouldClearCookies() {
        return false;
    }

    protected ProfilePage authorizeManually() {
        return openMainPage()
                .clickOnBookStoreApplicationButton()
                .clickOnLoginButton()
                .fillFieldsWith(demoUserLoginDataFromProperties())
                .login();
    }


}
