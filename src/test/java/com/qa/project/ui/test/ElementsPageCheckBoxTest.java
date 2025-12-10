package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageCheckBoxComponent;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ElementsPageCheckBoxTest extends UnauthorizedSelenideTest {

   @Test
    void shouldKeepOtherChildSelectedWhenOneChildToggled() {
        final ElementsPageCheckBoxComponent checkboxes = openElementsPage()
                .clickOnCheckBoxButton()
                .expandAll()
                .select(ElementsPageCheckBoxComponent.DESKTOP,
                        ElementsPageCheckBoxComponent.NOTES);

        assertTrue(checkboxes.isResultVisible());
        final List<String> selected = checkboxes.getSelectedItemsFromResult();


        System.out.println(selected);
        assertThat(selected, hasItems(ElementsPageCheckBoxComponent.COMMANDS));
    }

    @Test
    void shouldSelectCheckboxByLabel() {
        final ElementsPageCheckBoxComponent checkboxes = openElementsPage()
                .clickOnCheckBoxButton()
                .expandAll()
                .selectByLabel(ElementsPageCheckBoxComponent.REACT);

        assertTrue(checkboxes.isResultVisible());
        final List<String> selected = checkboxes.getSelectedItemsFromResult();
        assertThat(selected, containsInAnyOrder(ElementsPageCheckBoxComponent.REACT));
        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.REACT));
    }

    @Test
    void shouldDeselectAllCheckboxes() {
        final ElementsPageCheckBoxComponent checkboxes = openElementsPage()
                .clickOnCheckBoxButton()
                .expandAll()
                .select(ElementsPageCheckBoxComponent.NOTES,
                        ElementsPageCheckBoxComponent.REACT,
                        ElementsPageCheckBoxComponent.VEU)
                .deselectAll();

        assertFalse(checkboxes.isResultVisible());
        assertThat(checkboxes.getSelectedItemsFromResult().size(), org.hamcrest.Matchers.is(0));
    }

    @Test
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
        assertThat(afterCollapseExpand, containsInAnyOrder(initiallySelected.toArray(new String[0])));

        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.WORKSPACE));
        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.PUBLIC));
        assertTrue(checkboxes.isSelected(ElementsPageCheckBoxComponent.NOTES));
    }
}
