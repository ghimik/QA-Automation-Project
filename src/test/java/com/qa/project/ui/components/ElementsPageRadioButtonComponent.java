package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ElementsPageRadioButtonComponent {
    private final SelenideElement yesRadio = $("#yesRadio");
    private final SelenideElement impressiveRadio = $("#impressiveRadio");
    private final SelenideElement noRadio = $("#noRadio");
    private final SelenideElement resultText = $(".text-success");

    public enum Option {
        YES,
        IMPRESSIVE,
        NO
    }

    public ElementsPageRadioButtonComponent select(Option option) {
        switch (option) {
            case YES:
                yesRadio.parent().click();
                break;
            case IMPRESSIVE:
                impressiveRadio.parent().click();
                break;
            case NO:
                if (!noRadio.is(disabled)) {
                    noRadio.parent().click();
                }
                break;
        }
        return this;
    }

    public boolean isResultVisible() {
        return resultText.is(visible);
    }

    public String getSelectedValue() {
        return resultText.getText();
    }

    public boolean isOptionSelected(Option option) {
        return switch (option) {
            case YES -> yesRadio.is(checked);
            case IMPRESSIVE -> impressiveRadio.is(checked);
            case NO -> noRadio.is(checked);
        };
    }

    public boolean isNoOptionDisabled() {
        return noRadio.is(disabled);
    }

    public boolean isSelectedValueCorrect(Option expected) {
        if (!isResultVisible()) {
            return false;
        }

        final String actualText = getSelectedValue();
        return switch (expected) {
            case YES -> "Yes".equalsIgnoreCase(actualText);
            case IMPRESSIVE -> "Impressive".equalsIgnoreCase(actualText);
            case NO -> "No".equalsIgnoreCase(actualText);
        };
    }
}