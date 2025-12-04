package com.qa.project.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseSelenideTest {


    public static void setUp() {
        WebDriverManager.safaridriver().setup();
        Configuration.browser = "safari";
        Configuration.browserSize = "2880x1800";
        Configuration.headless = false;
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
        if (shouldClearCookies())
            Selenide.clearBrowserCookies();
    }

    public abstract boolean shouldClearCookies();

}