package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
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
        public SmallModal verifyOpened() {
            modalDialog.shouldBe(visible);
            modalTitle.shouldHave(text("Small Modal"));
            return this;
        }

        public String getTitle() {
            return modalTitle.getText();
        }

        public String getBodyText() {
            return modalBody.getText();
        }

        public ModalDialogsComponent close() {
            closeSmallModalButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }

        public ModalDialogsComponent closeWithX() {
            closeModalXButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }
    }

    public class LargeModal {
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

        public ModalDialogsComponent close() {
            closeLargeModalButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }

        public ModalDialogsComponent closeWithX() {
            closeModalXButton.click();
            modalDialog.should(disappear);
            return ModalDialogsComponent.this;
        }
    }

    public SmallModal openSmallModal() {
        smallModalButton.click();
        return new SmallModal();
    }

    public LargeModal openLargeModal() {
        largeModalButton.click();
        return new LargeModal();
    }

    public ModalDialogsComponent verifyComponentLoaded() {
        root.shouldBe(visible);
        root.$("h1").shouldHave(text("Modal Dialogs"));
        return this;
    }
}