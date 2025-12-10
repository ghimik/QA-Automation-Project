package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.model.TextBoxData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        final String fieldsNames = "(Name|Email|Current Address|Permananet Address)";
        final Pattern pattern = Pattern.compile(fieldsNames + "\\s*:\\s*(.*?)(?=" + fieldsNames + "\\s*:|$)");
        final Matcher matcher = pattern.matcher(raw);

        final Map<String, String> parsedToMap = new HashMap<>();
        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();
            parsedToMap.put(key, value);
        }

        final TextBoxData output = new TextBoxData();
        output.setFullName(parsedToMap.get("Name"));
        output.setEmail(parsedToMap.get("Email"));
        output.setCurrentAddress(parsedToMap.get("Current Address"));
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
