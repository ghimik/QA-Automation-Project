package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageButtonsComponent;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ElementsPageButtonsTest extends UnauthorizedSelenideTest {

    @Test
    void shouldShowMessageAfterDoubleClick() {
        final String message = openElementsPage()
                .clickOnButtonsButton()
                .doubleClick()
                .doubleClickParagraphText();

        assertNotNull(message);
        assertThat(message, containsString("You have done a double click"));
    }

    @Test
    void shouldShowMessageAfterRightClick() {
        final String message = openElementsPage()
                .clickOnButtonsButton()
                .rightClick()
                .rightClickParagraphText();

        assertNotNull(message);
        assertThat(message, containsString("You have done a right click"));
    }

    @Test
    void shouldShowMessageAfterDefaultClick() {
        final String message = openElementsPage()
                .clickOnButtonsButton()
                .defaultClick()
                .dynamicClickParagraphText();

        assertNotNull(message);
        assertThat(message, containsString("You have done a dynamic click"));
    }
}
