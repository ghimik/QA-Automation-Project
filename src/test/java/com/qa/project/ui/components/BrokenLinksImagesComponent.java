package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class BrokenLinksImagesComponent {

    private final SelenideElement root = $(".col-12.mt-4.col-md-6");
    private final SelenideElement header = root.$("h1.text-center");
    private final SelenideElement validImage = root.$("img[src*='Toolsqa.jpg']");
    private final SelenideElement brokenImage = root.$("img[src*='Toolsqa_1.jpg']");
    private final SelenideElement validLink = root.$("a[href='http://demoqa.com']");
    private final SelenideElement brokenLink = root.$("a[href*='status_codes/500']");

    public class ImageSection {

        @Step("Проверить, что валидное изображение отображается")
        public ImageSection shouldDisplayValidImage() {
            validImage.shouldBe(visible, image);
            return this;
        }

        @Step("Проверить, что битое изображение отображается как broken")
        public ImageSection shouldDisplayBrokenImage() {
            brokenImage.shouldBe(visible);
            brokenImage.shouldHave(attributeMatching("naturalWidth", "0"));
            return this;
        }
    }

    public class LinkSection {

        @Step("Проверить, что валидная ссылка открывается в новой вкладке")
        public LinkSection verifyValidLinkRedirects() {
            final String originalWindow = webdriver().driver().getWebDriver().getWindowHandle();
            validLink.click();
            switchTo().window(1);
            webdriver().driver().getWebDriver().close();
            switchTo().window(originalWindow);
            return this;
        }

        @Step("Кликнуть на битую ссылку (должна быть ошибка)")
        public LinkSection verifyBrokenLinkShowsError() {
            brokenLink.click();
            return this;
        }
    }

    public ImageSection imageSection = new ImageSection();
    public LinkSection linkSection = new LinkSection();

    @Step("Проверить видимость компонента Broken Links - Images")
    public BrokenLinksImagesComponent shouldBeVisible() {
        root.shouldBe(visible);
        header.shouldHave(text("Broken Links - Images"));
        return this;
    }

    @Step("Проверить, отображается ли валидное изображение")
    public boolean isValidImageDisplayed() {
        return validImage.isImage();
    }

    @Step("Проверить, отображается ли битое изображение")
    public boolean isBrokenImageDisplayed() {
        return brokenImage.isDisplayed() &&
                Integer.parseInt(Objects.requireNonNull(brokenImage.getAttribute("naturalWidth"))) == 0;
    }

    @Step("Получить src валидного изображения")
    public String getValidImageSrc() {
        return validImage.getAttribute("src");
    }

    @Step("Получить src битого изображения")
    public String getBrokenImageSrc() {
        return brokenImage.getAttribute("src");
    }
}