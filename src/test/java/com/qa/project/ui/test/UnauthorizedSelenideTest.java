package com.qa.project.ui.test;

public abstract class UnauthorizedSelenideTest extends BaseSelenideTest {

    @Override
    public boolean shouldClearCookies() {
        return true;
    }
}
