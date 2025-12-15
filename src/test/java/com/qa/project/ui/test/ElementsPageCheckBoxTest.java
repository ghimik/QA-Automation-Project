package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageCheckBoxComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("Тестирование раздела Elements")
@Feature("Check Box")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/checkbox")
@Severity(SeverityLevel.CRITICAL)
public class ElementsPageCheckBoxTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Выбор соседних чекбоксов")
    @Description("При выборе одного дочернего чекбокса, другие дочерние должны оставаться выбранными")
    void shouldKeepOtherChildSelectedWhenOneChildToggled() {
        final ElementsPageCheckBoxComponent checkboxes = openElementsPage()
                .clickOnCheckBoxButton()
                .expandAll()
                .select(ElementsPageCheckBoxComponent.DESKTOP,
                        ElementsPageCheckBoxComponent.NOTES);

        assertTrue(checkboxes.isResultVisible(), "Результат должен отображаться после выбора");
        final List<String> selected = checkboxes.getSelectedItemsFromResult();

        assertThat("COMMANDS должен быть автоматически быть выбранным при выборе DESKTOP и NOTES",
                selected, hasItems(ElementsPageCheckBoxComponent.COMMANDS));
    }

    @Test
    @Story("Выбор по названию")
    @Description("Проверка выбора чекбокса по текстовому лейблу")
    void shouldSelectCheckboxByLabel() {
        final ElementsPageCheckBoxComponent checkboxes = openElementsPage()
                .clickOnCheckBoxButton()
                .expandAll()
                .selectByLabel(ElementsPageCheckBoxComponent.REACT);

        assertTrue(checkboxes.isResultVisible(), "Результат должен отображаться после выбора");
        final List<String> selected = checkboxes.getSelectedItemsFromResult();

        assertThat("Должен быть выбран только REACT",
                selected, containsInAnyOrder(ElementsPageCheckBoxComponent.REACT));
        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.REACT),
                "Чекбокс REACT должен быть выбран");
    }

    @Test
    @Story("Сброс выбора")
    @Description("Проверка функции сброса всех выбранных чекбоксов")
    void shouldDeselectAllCheckboxes() {
        final ElementsPageCheckBoxComponent checkboxes = openElementsPage()
                .clickOnCheckBoxButton()
                .expandAll()
                .select(ElementsPageCheckBoxComponent.NOTES,
                        ElementsPageCheckBoxComponent.REACT,
                        ElementsPageCheckBoxComponent.VEU)
                .deselectAll();

        assertFalse(checkboxes.isResultVisible(), "Результат не должен отображаться после сброса");
        assertThat("После deselectAll не должно быть выбранных элементов",
                checkboxes.getSelectedItemsFromResult().size(), is(0));
    }

    @Test
    @Story("Сохранение состояния при свертывании/развертывании")
    @Description("Выбранные чекбоксы должны сохранять состояние после collapse/expand")
    void doKeepSelectedCheckboxesAfterCollapseAndExpand() {
        final ElementsPageCheckBoxComponent checkboxes = openElementsPage()
                .clickOnCheckBoxButton()
                .toggleExpandByLabel(ElementsPageCheckBoxComponent.HOME)
                .toggleExpandByLabel(ElementsPageCheckBoxComponent.DESKTOP)
                .toggleExpandByLabel(ElementsPageCheckBoxComponent.DOCUMENTS)
                .toggleExpandByLabel(ElementsPageCheckBoxComponent.OFFICE)
                .select(ElementsPageCheckBoxComponent.PUBLIC,
                        ElementsPageCheckBoxComponent.NOTES,
                        ElementsPageCheckBoxComponent.WORKSPACE);

        final List<String> initiallySelected = checkboxes.getSelectedItemsFromResult();

        checkboxes.collapseAll();
        checkboxes.expandAll();

        final List<String> afterCollapseExpand = checkboxes.getSelectedItemsFromResult();
        assertThat("Выбранные элементы должны сохраниться после collapse/expand",
                afterCollapseExpand, containsInAnyOrder(initiallySelected.toArray(new String[0])));

        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.WORKSPACE),
                "WORKSPACE должен остаться выбранным");
        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.PUBLIC),
                "PUBLIC должен остаться выбранным");
        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.NOTES),
                "NOTES должен остаться выбранным");
    }
}