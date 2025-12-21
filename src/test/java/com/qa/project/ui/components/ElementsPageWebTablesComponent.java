package com.qa.project.ui.components;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.model.EmployeeRecord;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.*;

public class ElementsPageWebTablesComponent {
    private final SelenideElement addButton = $("#addNewRecordButton");
    private final SelenideElement searchInput = $("#searchBox");

    private final ElementsCollection tableRows = $$(".rt-tr-group .rt-tr:not(.-padRow)");
    private final ElementsCollection firstNameCells = $$(".rt-td:nth-child(1)");
    private final ElementsCollection lastNameCells = $$(".rt-td:nth-child(2)");
    private final ElementsCollection ageCells = $$(".rt-td:nth-child(3)");
    private final ElementsCollection emailCells = $$(".rt-td:nth-child(4)");
    private final ElementsCollection salaryCells = $$(".rt-td:nth-child(5)");
    private final ElementsCollection departmentCells = $$(".rt-td:nth-child(6)");


    private final SelenideElement previousPageButton = $(".-previous .-btn");
    private final SelenideElement nextPageButton = $(".-next .-btn");
    private final SelenideElement pageInput = $(".-pageJump input");
    private final SelenideElement pageSizeSelect = $(".-pageSizeOptions select");
    private final SelenideElement totalPages = $(".-totalPages");


    public SelenideElement getEditButton(int rowIndex) {
        return $x(String.format("(//span[@data-toggle='tooltip' and @title='Edit'])[%d]", rowIndex + 1));
    }

    public SelenideElement getDeleteButton(int rowIndex) {
        return $x(String.format("(//span[@data-toggle='tooltip' and @title='Delete'])[%d]", rowIndex + 1));
    }

    @Step("Получить все записи из таблицы")
    public List<EmployeeRecord> getAllRecords() {
        final List<EmployeeRecord> records = new ArrayList<>();

        for (int i = 0; i < tableRows.size(); i++) {
            EmployeeRecord record = new EmployeeRecord();
            record.setRowNumber(i);
            record.setFirstName(firstNameCells.get(i).getText());
            record.setLastName(lastNameCells.get(i).getText());
            record.setAge(Integer.parseInt(ageCells.get(i).getText()));
            record.setEmail(emailCells.get(i).getText());
            record.setSalary(Integer.parseInt(salaryCells.get(i).getText()));
            record.setDepartment(departmentCells.get(i).getText());

            records.add(record);
        }

        return records;
    }

    @Step("Найти записи по поисковому запросу: {searchTerm}")
    public List<EmployeeRecord> searchRecords(String searchTerm) {
        searchInput.setValue(searchTerm);
        return getAllRecords();
    }

    @Step("Проверить, существует ли запись: {recordToFind}")
    public boolean recordExists(EmployeeRecord recordToFind) {
        return getAllRecords().stream()
                .anyMatch(record ->
                        record.getFirstName().equals(recordToFind.getFirstName()) &&
                                record.getLastName().equals(recordToFind.getLastName()) &&
                                record.getEmail().equals(recordToFind.getEmail())
                );
    }

    @Step("Получить запись по индексу: {index}")
    public EmployeeRecord getRecordByIndex(int index) {
        final List<EmployeeRecord> records = getAllRecords();
        if (index < 0 || index >= records.size()) {
            throw new IllegalArgumentException("Invalid row index: " + index);
        }
        return records.get(index);
    }

    @Step("Удалить запись с индексом: {rowIndex}")
    public ElementsPageWebTablesComponent deleteRecord(int rowIndex) {
        getDeleteButton(rowIndex).click();
        return this;
    }

    @Step("Редактировать запись с индексом: {rowIndex}")
    public ElementsPageWebTablesComponent editRecord(int rowIndex) {
        getEditButton(rowIndex).click();
        return this;
    }

    @Step("Нажать кнопку Add (добавить новую запись)")
    public ElementsPageWebTablesComponent clickAdd() {
        addButton.click();
        return this;
    }

    @Step("Проверить, что в таблице есть записи")
    public boolean hasRecords() {
        return !tableRows.isEmpty() &&
                !firstNameCells.first().getText().trim().isEmpty();
    }

    @Step("Получить количество записей в таблице")
    public int getRecordCount() {
        return (int) tableRows.asDynamicIterable().stream()
                .filter(row -> !row.getText().trim().isEmpty())
                .count();
    }

    @Step("Очистить поле поиска")
    public ElementsPageWebTablesComponent clearSearch() {
        searchInput.clear();
        return this;
    }

    @Step("Установить размер страницы: {size}")
    public ElementsPageWebTablesComponent setPageSize(String size) {
        pageSizeSelect.selectOption(size);
        return this;
    }

    @Step("Перейти на страницу: {pageNumber}")
    public ElementsPageWebTablesComponent goToPage(int pageNumber) {
        pageInput.setValue(String.valueOf(pageNumber)).pressEnter();
        return this;
    }

    @Step("Получить текущую страницу")
    public int getCurrentPage() {
        return Integer.parseInt(Objects.requireNonNull(pageInput.getValue()));
    }

    @Step("Получить общее количество страниц")
    public int getTotalPages() {
        return Integer.parseInt(totalPages.getText());
    }

    @Step("Перейти на следующую страницу")
    public ElementsPageWebTablesComponent nextPage() {
        if (!nextPageButton.is(disabled)) {
            nextPageButton.click();
        }
        return this;
    }

    @Step("Перейти на предыдущую страницу")
    public ElementsPageWebTablesComponent previousPage() {
        if (!previousPageButton.is(disabled)) {
            previousPageButton.click();
        }
        return this;
    }

    public ElementsPageWebTablesComponent waitForRecords() {
        tableRows.shouldBe(CollectionCondition.sizeGreaterThan(0));
        return this;
    }
}
