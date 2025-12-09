package com.qa.project.ui.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ElementsPageCheckBoxComponent {
    private final SelenideElement expandAllButton = $("button[title='Expand all']");
    private final SelenideElement collapseAllButton = $("button[title='Collapse all']");
    private final SelenideElement resultSpan = $(".text-success");

    private final ElementsCollection allCheckboxes = $$("input[type='checkbox']");

    private SelenideElement checkboxById(String id) {
        return $("#tree-node-" + id);
    }

    public ElementsPageCheckBoxComponent expandAll() {
        expandAllButton.click();
        return this;
    }

    public ElementsPageCheckBoxComponent collapseAll() {
        collapseAllButton.click();
        return this;
    }

    public ElementsPageCheckBoxComponent select(String... checkboxIds) {
        for (String id : checkboxIds) {
            checkboxById(id).parent().click();
        }
        return this;
    }

    public ElementsPageCheckBoxComponent selectByLabel(String label) {
        $x(String.format("//span[contains(@class, 'rct-title') and text()='%s']/preceding-sibling::label", label))
                .click();
        return this;
    }

    public ElementsPageCheckBoxComponent deselectAll() {
        allCheckboxes.filter(checked).forEach(checkbox -> checkbox.parent().click());
        return this;
    }

    public boolean isResultVisible() {
        return resultSpan.is(visible);
    }

    public String getResultText() {
        return resultSpan.getText();
    }

    public List<String> getSelectedItemsFromResult() {
        if (!isResultVisible()) {
            return List.of();
        }

        final String resultText = getResultText();
        return resultText.lines()
                .skip(1)
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isSelected(String checkboxId) {
        return checkboxById(checkboxId).is(checked);
    }

    public int getSelectedCount() {
        return (int) allCheckboxes.asDynamicIterable().stream().filter(checkbox -> checkbox.is(checked)).count();
    }

    public static final String HOME = "home";
    public static final String DESKTOP = "desktop";
    public static final String NOTES = "notes";
    public static final String COMMANDS = "commands";
    public static final String DOCUMENTS = "documents";
    public static final String WORKSPACE = "workspace";
    public static final String REACT = "react";
    public static final String ANGULAR = "angular";
    public static final String VEU = "veu";
    public static final String OFFICE = "office";
    public static final String PUBLIC = "public";
    public static final String PRIVATE = "private";
    public static final String CLASSIFIED = "classified";
    public static final String GENERAL = "general";
    public static final String DOWNLOADS = "downloads";
    public static final String WORD_FILE = "wordFile";
    public static final String EXCEL_FILE = "excelFile";
}