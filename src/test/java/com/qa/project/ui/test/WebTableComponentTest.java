package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageWebTablesComponent;
import com.qa.project.ui.model.EmployeeRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.*;

public class WebTableComponentTest extends UnauthorizedSelenideTest {

    @Test
    void shouldFilterRecordsBySearch() {
        final ElementsPageWebTablesComponent table = openElementsPage()
                .clickOnWebTablesButton()
                .waitForRecords();

        final List<EmployeeRecord> filtered = table.searchRecords("Cierra");

        assertFalse(filtered.isEmpty());
        assertTrue(filtered.stream().allMatch(r -> r.getFirstName().contains("Cierra")
                || r.getLastName().contains("Cierra")
                || r.getEmail().contains("Cierra")));
    }

    @Test
    void shouldDeleteRecord() {
        final ElementsPageWebTablesComponent table = openElementsPage()
                .clickOnWebTablesButton()
                .waitForRecords();

        final int before = table.getRecordCount();
        assertTrue(before > 0);

        table.deleteRecord(0).waitForRecords();
        final int after = table.getRecordCount();

        assertEquals(before - 1, after);
    }





}
