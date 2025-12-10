package com.qa.project.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.pages.MainPage;
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

    public ApiLinksBranch clickOnCreatedLink = clickOnLinkWrapper(createdLink);
    public ApiLinksBranch clickOnNoContentLink = clickOnLinkWrapper(noContentLink);
    public ApiLinksBranch clickOnMovedLink = clickOnLinkWrapper(movedLink);
    public ApiLinksBranch clickOnBadRequestLink = clickOnLinkWrapper(badRequestLink);
    public ApiLinksBranch clickOnUnauthorizedLink = clickOnLinkWrapper(unauthorizedLink);
    public ApiLinksBranch clickOnForbiddenLink = clickOnLinkWrapper(forbiddenLink);
    public ApiLinksBranch clickOnNotFoundLink = clickOnLinkWrapper(notFoundLink);
    public TabLinksBranch<MainPage> clickOnSimpleLink = clickOnTabLinkWrapper(simpleLink, MainPage::new);
    public TabLinksBranch<MainPage> clickOnDynamicLink = clickOnTabLinkWrapper(dynamicLink, MainPage::new);

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

        public ElementsPageLinksComponent switchToPreviousTab() {
            Selenide.switchTo().window(0);
            return parent;
        }

        public ElementsPageLinksComponent closeCurrentTab() {
            Selenide.closeWindow();
            return parent;
        }
    }



    private ApiLinksBranch clickOnLinkWrapper(SelenideElement link) {
        link.shouldBe(visible).click();
        return new ApiLinksBranch();
    }

    private <T> TabLinksBranch<T> clickOnTabLinkWrapper(SelenideElement link, Supplier<T> pageSupplier) {
        link.shouldBe(visible).click();
        return new TabLinksBranch<T>(pageSupplier.get(), this);
    }



}
