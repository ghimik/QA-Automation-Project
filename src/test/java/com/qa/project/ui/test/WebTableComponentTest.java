package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageWebTablesComponent;
import com.qa.project.ui.model.EmployeeRecord;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Тестирование раздела Elements")
@Feature("Web Tables")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/webtables")
@Severity(SeverityLevel.CRITICAL)
public class WebTableComponentTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Фильтрация записей")
    @Description("Проверка поиска и фильтрации записей в таблице по строке 'Cierra'")
    void shouldFilterRecordsBySearch() {
        Allure.parameter("Поисковый запрос", "Cierra");

        final ElementsPageWebTablesComponent table = openElementsPage()
                .clickOnWebTablesButton()
                .waitForRecords();

        final List<EmployeeRecord> filtered = table.searchRecords("Cierra");

        assertFalse(filtered.isEmpty(),
                "После поиска 'Cierra' должны найтись записи");

        assertTrue(filtered.stream().allMatch(r ->
                        r.getFirstName().contains("Cierra") ||
                                r.getLastName().contains("Cierra") ||
                                r.getEmail().contains("Cierra")),
                "Все найденные записи должны содержать 'Cierra' в firstName, lastName или email");
    }

    @Test
    @Story("Удаление записи")
    @Description("Проверка удаления записи из таблицы и уменьшения количества записей")
    void shouldDeleteRecord() {
        final ElementsPageWebTablesComponent table = openElementsPage()
                .clickOnWebTablesButton()
                .waitForRecords();

        final int before = table.getRecordCount();
        assertTrue(before > 0,
                "В таблице должны быть записи перед удалением");

        table.deleteRecord(0).waitForRecords();
        final int after = table.getRecordCount();

        assertEquals(before - 1, after,
                String.format("После удаления количество записей должно уменьшиться с %d до %d", before, after));
    }
}