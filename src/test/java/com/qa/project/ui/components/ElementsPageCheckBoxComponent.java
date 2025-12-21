package com.qa.project.ui.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ElementsPageCheckBoxComponent {
    private final SelenideElement expandAllButton = $("button[title='Expand all']");
    private final SelenideElement collapseAllButton = $("button[title='Collapse all']");
    private final ElementsCollection resultSpan = $$(".text-success");
    private final ElementsCollection allCheckboxes = $$("input[type='checkbox']");

    private SelenideElement checkboxById(String id) {
        return $("#tree-node-" + id);
    }

    private SelenideElement toggleExpandButtonByLabel(String label) {
        return $x(String.format("//label[@for='tree-node-%s']/../button", label));
    }

    @Step("Раскрыть все чекбоксы (Expand all)")
    public ElementsPageCheckBoxComponent expandAll() {
        expandAllButton.click();
        return this;
    }

    @Step("Свернуть все чекбоксы (Collapse all)")
    public ElementsPageCheckBoxComponent collapseAll() {
        collapseAllButton.click();
        return this;
    }

    @Step("Затогглить состояние узла '{label}'")
    public ElementsPageCheckBoxComponent toggleExpandByLabel(String label) {
        toggleExpandButtonByLabel(label).click();
        return this;
    }

    @Step("Выбрать чекбоксы по ID: {checkboxIds}")
    public ElementsPageCheckBoxComponent select(String... checkboxIds) {
        for (String id : checkboxIds) {
            checkboxById(id).parent().click();
        }
        return this;
    }

    @Step("Выбрать чекбокс по label: '{label}'")
    public ElementsPageCheckBoxComponent selectByLabel(String label) {
        $x(String.format("//label[@for='tree-node-%s']", label))
                .click();
        return this;
    }

    @Step("Снять выделение со всех чекбоксов")
    public ElementsPageCheckBoxComponent deselectAll() {
        allCheckboxes.filter(checked).forEach(checkbox -> checkbox.parent().click());
        return this;
    }

    @Step("Проверить, что результат выбора отображается")
    public boolean isResultVisible() {
        return !resultSpan.isEmpty();
    }

    public String getResultText() {
        return resultSpan.asDynamicIterable().stream().map(SelenideElement::text).collect(Collectors.joining(" "));
    }

    public List<String> getSelectedItemsFromResult() {
        final String resultText = getResultText();
        return List.of(resultText.split(" "));
    }

    @Step("Проверить, выбран ли чекбокс с ID '{checkboxId}'")
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