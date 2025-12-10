package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageRadioButtonComponent;
import com.qa.project.ui.components.ElementsPageRadioButtonComponent.Option;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElementsPageRadioButtonTest extends UnauthorizedSelenideTest {

   @Test
    void shouldSelectYesOption() {
        final ElementsPageRadioButtonComponent radio = openElementsPage()
                .clickOnRadioButtonButton()
                .select(Option.YES);

        assertTrue(radio.isOptionSelected(Option.YES));
        assertTrue(radio.isSelectedValueCorrect(Option.YES));
    }

    @Test
    void shouldSelectImpressiveOption() {
        final ElementsPageRadioButtonComponent radio = openElementsPage()
                .clickOnRadioButtonButton()
                .select(Option.IMPRESSIVE);

        assertTrue(radio.isOptionSelected(Option.IMPRESSIVE));
        assertTrue(radio.isSelectedValueCorrect(Option.IMPRESSIVE));
    }

    @Test
    void shouldHaveNoOptionDisabled() {
        final ElementsPageRadioButtonComponent radio = openElementsPage()
                .clickOnRadioButtonButton();

        assertTrue(radio.isNoOptionDisabled());
        radio.select(Option.NO);
        assertFalse(radio.isOptionSelected(Option.NO));
    }
}
