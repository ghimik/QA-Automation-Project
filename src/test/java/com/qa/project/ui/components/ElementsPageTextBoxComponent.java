package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.model.TextBoxData;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ElementsPageTextBoxComponent {
    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement currentAddressTextarea = $("#currentAddress");
    private final SelenideElement permanentAddressTextarea = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputDiv = $("#output");


    public ElementsPageTextBoxComponent fill(TextBoxData formData) {
        fullNameInput.setValue(formData.getFullName());
        emailInput.setValue(formData.getEmail());
        currentAddressTextarea.setValue(formData.getCurrentAddress());
        permanentAddressTextarea.setValue(formData.getPermanentAddress());
        return this;
    }

    public ElementsPageTextBoxComponent submit() {
        submitButton.click();
        return this;
    }

    public boolean isOutputVisible() {
        return outputDiv.is(visible);
    }

    /**
     * это не опечатка, это такой результирующий div
     */
    public TextBoxData getOutputDivRawData() {
        final String raw = outputDiv.getText();
        final Map<String, String> parsedToMap = Arrays.stream(
                raw.split("\n"))
                .collect(Collectors.toMap(row -> row.split(":")[0].trim(),
                        row -> row.split(":")[1].trim()));
        final TextBoxData output = new TextBoxData();

        if (parsedToMap.get("Name") != null)
            output.setFullName(parsedToMap.get("Name"));
        if (parsedToMap.get("Email") != null)
            output.setEmail(parsedToMap.get("Email"));
        if (parsedToMap.get("Address") != null)
            output.setCurrentAddress(parsedToMap.get("Current Address"));
        if (parsedToMap.get("Permanent Address") != null)
            output.setPermanentAddress(parsedToMap.get("Permananet Address"));
        return output;
    }

    public void clearForm() {
        fullNameInput.clear();
        emailInput.clear();
        currentAddressTextarea.clear();
        permanentAddressTextarea.clear();
    }
}
