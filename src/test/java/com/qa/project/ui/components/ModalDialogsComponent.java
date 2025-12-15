package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class ModalDialogsComponent {

    private final SelenideElement root = $("#modalWrapper");

    private final SelenideElement smallModalButton = $("#showSmallModal");
    private final SelenideElement largeModalButton = $("#showLargeModal");

    private final SelenideElement modalDialog = $(".modal.show");
    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement modalBody = $(".modal-body");

    private final SelenideElement closeSmallModalButton = $("#closeSmallModal");
    private final SelenideElement closeLargeModalButton = $("[id*='closeLargeModal']");
    private final SelenideElement closeModalXButton = $(".modal-header .close");

    public class SmallModal {

        @Step("Проверить, что Small Modal открыт")
        public SmallModal verifyOpened() {
            modalDialog.shouldBe(visible);
            modalTitle.shouldHave(text("Small Modal"));
            return this;
        }

        @Step("Получить заголовок Small Modal")
        public String getTitle() {
            return modalTitle.getText();
        }

        public String getBodyText() {
            return modalBody.getText();
        }

        @Step("Закрыть Small Modal кнопкой Close")
        public ModalDialogsComponent close() {
            closeSmallModalButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }

        @Step("Закрыть Small Modal крестиком (X)")
        public ModalDialogsComponent closeWithX() {
            closeModalXButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }
    }

    public class LargeModal {

        @Step("Проверить, что Large Modal открыт")
        public LargeModal verifyOpened() {
            modalDialog.shouldBe(visible);
            modalTitle.shouldHave(text("Large Modal"));
            return this;
        }

        public String getTitle() {
            return modalTitle.getText();
        }

        public String getBodyText() {
            return modalBody.getText();
        }

        @Step("Закрыть Large Modal кнопкой Close")
        public ModalDialogsComponent close() {
            closeLargeModalButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }

        @Step("Закрыть Large Modal крестиком (X)")
        public ModalDialogsComponent closeWithX() {
            closeModalXButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }
    }

    @Step("Открыть Small Modal")
    public SmallModal openSmallModal() {
        smallModalButton.click();
        return new SmallModal();
    }

    @Step("Открыть Large Modal")
    public LargeModal openLargeModal() {
        largeModalButton.click();
        return new LargeModal();
    }

    @Step("Проверить загрузку компонента Modal Dialogs")
    public ModalDialogsComponent verifyComponentLoaded() {
        root.shouldBe(visible);
        root.$("h1").shouldHave(text("Modal Dialogs"));
        return this;
    }
}