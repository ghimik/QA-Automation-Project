package com.qa.project.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.pages.MainPage;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.qa.project.common.util.ResponseParser.parseResponse;

public class ElementsPageLinksComponent {



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiLinkResponse {
        private int responseCode;
        private String responseMessage;
    }


    private final SelenideElement simpleLink = $("#simpleLink");
    private final SelenideElement dynamicLink = $("#dynamicLink");

    private final SelenideElement createdLink = $("#created");
    private final SelenideElement noContentLink = $("#no-content");
    private final SelenideElement movedLink = $("#moved");
    private final SelenideElement badRequestLink = $("#bad-request");
    private final SelenideElement unauthorizedLink = $("#unauthorized");
    private final SelenideElement forbiddenLink = $("#forbidden");
    private final SelenideElement notFoundLink = $("#invalid-url");

    private final SelenideElement linkResponse = $("#linkResponse");

    @Step("Кликнуть на ссылку Created (201)")
    public ApiLinksBranch clickOnCreatedLink() { return clickOnLinkWrapper(createdLink); }

    @Step("Кликнуть на ссылку No Content (204)")
    public ApiLinksBranch clickOnNoContentLink() { return clickOnLinkWrapper(noContentLink); }

    @Step("Кликнуть на ссылку Moved (301)")
    public ApiLinksBranch clickOnMovedLink() { return  clickOnLinkWrapper(movedLink);}

    @Step("Кликнуть на ссылку Bad Request (400)")
    public ApiLinksBranch clickOnBadRequestLink() { return  clickOnLinkWrapper(badRequestLink);}

    @Step("Кликнуть на ссылку Unauthorized (401)")
    public ApiLinksBranch clickOnUnauthorizedLink() { return  clickOnLinkWrapper(unauthorizedLink);}

    @Step("Кликнуть на ссылку Forbidden (403)")
    public ApiLinksBranch clickOnForbiddenLink() { return  clickOnLinkWrapper(forbiddenLink);}

    @Step("Кликнуть на ссылку Not Found (404)")
    public ApiLinksBranch clickOnNotFoundLink() { return clickOnLinkWrapper(notFoundLink);}

    @Step("Кликнуть на Simple Link (открывает новую вкладку)")
    public TabLinksBranch<MainPage> clickOnSimpleLink() { return clickOnTabLinkWrapper(simpleLink, MainPage::new);}

    @Step("Кликнуть на Dynamic Link (открывает новую вкладку)")
    public TabLinksBranch<MainPage> clickOnDynamicLink() { return clickOnTabLinkWrapper(dynamicLink, MainPage::new);}


    public class ApiLinksBranch {

        private String getResponseText() {
            return linkResponse.shouldBe(visible).getText();
        }

        public ApiLinkResponse getApiLinkResponse = parseResponse(linkResponse.shouldBe(visible).getText());
    }

    @RequiredArgsConstructor
    public static class TabLinksBranch<T> {
        private final T pageObject;
        private final ElementsPageLinksComponent parent;

        public T getCurrentPageObject() {
            return pageObject;
        }

        @Step("Переключиться на предыдущую вкладку")
        public ElementsPageLinksComponent switchToPreviousTab() {
            Selenide.switchTo().window(0);
            return parent;
        }

        @Step("Закрыть текущую вкладку")
        public ElementsPageLinksComponent closeCurrentTab() {
            Selenide.closeWindow();
            return parent;
        }
    }

    @Step("Кликнуть на API ссылку: {linkName}")
    public ApiLinksBranch clickOnApiLink(String linkName) {
        return switch (linkName.toLowerCase()) {
            case "created" -> clickOnCreatedLink();
            case "no content" -> clickOnNoContentLink();
            case "moved" -> clickOnMovedLink();
            case "bad request" -> clickOnBadRequestLink();
            case "unauthorized" -> clickOnUnauthorizedLink();
            case "forbidden" -> clickOnForbiddenLink();
            case "not found" -> clickOnNotFoundLink();
            default -> throw new IllegalArgumentException("Unknown API link name: " + linkName);
        };
    }



    private ApiLinksBranch clickOnLinkWrapper(SelenideElement link) {
        link.shouldBe(visible).click();
        return new ApiLinksBranch();
    }

    private <T> TabLinksBranch<T> clickOnTabLinkWrapper(SelenideElement link, Supplier<T> pageSupplier) {
        link.shouldBe(visible).click();
        return new TabLinksBranch<>(pageSupplier.get(), this);
    }



}
