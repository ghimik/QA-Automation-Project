package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.qa.project.ui.components.PractiseFormComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FormsPage {

    private final By practiseForm = By.xpath("//span[contains(text(), 'Practice Form')]");

    public PractiseFormComponent clickOnPractiseFormButton() {
        $(practiseForm).click();
        return new PractiseFormComponent();
    }

    public static FormsPage openFormsPage() {
        Selenide.open("/forms");
        return new FormsPage();
    }

}
