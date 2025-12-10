package com.qa.project.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.components.*;

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


    public ElementsPageTextBoxComponent clickOnTextBoxButton() {
        textBoxMenuItem.click();
        return new ElementsPageTextBoxComponent();
    }

    public ElementsPageCheckBoxComponent clickOnCheckBoxButton() {
        checkBoxMenuItem.click();
        return new ElementsPageCheckBoxComponent();
    }

    public ElementsPageRadioButtonComponent clickOnRadioButtonButton() {
        radioButtonMenuItem.click();
        return new ElementsPageRadioButtonComponent();
    }

    public ElementsPageWebTablesComponent clickOnWebTablesButton() {
        webTablesMenuItem.click();
        return new ElementsPageWebTablesComponent();
    }

    public ElementsPageButtonsComponent clickOnButtonsButton() {
        buttonsMenuItem.click();
        return new ElementsPageButtonsComponent();
    }

    public ElementsPageLinksComponent clickOnLinksButton() {
        linksMenuItem.click();
        return new ElementsPageLinksComponent();
    }

    public BrokenLinksImagesComponent clickOnBrokenLinksImagesButton() {
        brokenLinksImagesMenuItem.click();
        return new BrokenLinksImagesComponent();
    }


    public static ElementsPage open() {
        Selenide.open("/elements");
        return new ElementsPage();
    }
}
