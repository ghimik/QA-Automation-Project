package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.qa.project.ui.components.PractiseFormComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FormsPage {

    private final By practiseForm = By.xpath("//span[contains(text(), 'Practice Form')]");

    @Step("Кликнуть на меню 'Practice Form'")
    public PractiseFormComponent clickOnPractiseFormButton() {
        $(practiseForm).click();
        return new PractiseFormComponent();
    }

    @Step("Открыть страницу Forms по URL")
    public static FormsPage openFormsPage() {
        Selenide.open("/forms");
        return new FormsPage();
    }
}