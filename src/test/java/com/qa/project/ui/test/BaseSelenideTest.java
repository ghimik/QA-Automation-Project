package com.qa.project.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseSelenideTest {


    public static void setUp() {
//        String browser = System.getProperty("browser",
//                System.getenv().getOrDefault("BROWSER", "chrome"));
//
//        switch (browser.toLowerCase()) {
//            case "safari":
//                WebDriverManager.safaridriver().setup();
//                Configuration.browser = "safari";
//                break;
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                Configuration.browser = "firefox";
//                break;
//            case "edge":
//                WebDriverManager.edgedriver().setup();
//                Configuration.browser = "edge";
//                break;
//            case "opera":
//                WebDriverManager.operadriver().setup();
//                Configuration.browser = "opera";
//                break;
//            case "chrome":
//            default:
//                WebDriverManager.chromedriver().setup();
//                Configuration.browser = "chrome";
//                break;
//        }

        Configuration.browser = "chrome";
        // WebDriverManager.chromedriver().setup();
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 20000;
        Configuration.browserSize="1920x1080";
        Configuration.baseUrl = "https://demoqa.com";




        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
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
        SelenideLogger.removeListener("AllureSelenide");

    }

    public abstract boolean shouldClearCookies();

}