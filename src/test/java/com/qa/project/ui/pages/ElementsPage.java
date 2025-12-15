package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.components.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ElementsPage {

    private final SelenideElement textBoxMenuItem = $x("//span[@class='text'][contains(text(), 'Text Box')]");
    private final SelenideElement checkBoxMenuItem = $x("//span[@class='text'][contains(text(), 'Check Box')]");
    private final SelenideElement radioButtonMenuItem = $x("//span[@class='text'][contains(text(), 'Radio Button')]");
    private final SelenideElement webTablesMenuItem = $x("//span[@class='text'][contains(text(), 'Web Tables')]");
    private final SelenideElement buttonsMenuItem = $x("//span[@class='text'][contains(text(), 'Buttons')]");
    private final SelenideElement linksMenuItem = $x("//span[@class='text'][contains(text(), 'Links')]");
    private final SelenideElement brokenLinksImagesMenuItem = $x("//span[@class='text'][contains(text(), 'Broken Links - Images')]");
    private final SelenideElement uploadDownloadMenuItem = $x("//span[@class='text'][contains(text(), 'Upload and Download')]");
    private final SelenideElement dynamicPropertiesMenuItem = $x("//span[@class='text'][contains(text(), 'Dynamic Properties')]");


    @Step("Кликнуть на меню 'Text Box'")
    public ElementsPageTextBoxComponent clickOnTextBoxButton() {
        textBoxMenuItem.click();
        return new ElementsPageTextBoxComponent();
    }

    @Step("Кликнуть на меню 'Check Box'")
    public ElementsPageCheckBoxComponent clickOnCheckBoxButton() {
        checkBoxMenuItem.click();
        return new ElementsPageCheckBoxComponent();
    }

    @Step("Кликнуть на меню 'Radio Button'")
    public ElementsPageRadioButtonComponent clickOnRadioButtonButton() {
        radioButtonMenuItem.click();
        return new ElementsPageRadioButtonComponent();
    }

    @Step("Кликнуть на меню 'Web Tables'")
    public ElementsPageWebTablesComponent clickOnWebTablesButton() {
        webTablesMenuItem.click();
        return new ElementsPageWebTablesComponent();
    }

    @Step("Кликнуть на меню 'Buttons'")
    public ElementsPageButtonsComponent clickOnButtonsButton() {
        buttonsMenuItem.click();
        return new ElementsPageButtonsComponent();
    }

    @Step("Кликнуть на меню 'Links'")
    public ElementsPageLinksComponent clickOnLinksButton() {
        linksMenuItem.click();
        return new ElementsPageLinksComponent();
    }

    @Step("Кликнуть на меню 'Broken Links - Images'")
    public BrokenLinksImagesComponent clickOnBrokenLinksImagesButton() {
        brokenLinksImagesMenuItem.click();
        return new BrokenLinksImagesComponent();
    }

    @Step("Открыть страницу Elements по URL")
    public static ElementsPage openElementsPage() {
        Selenide.open("/elements");
        return new ElementsPage();
    }
}
