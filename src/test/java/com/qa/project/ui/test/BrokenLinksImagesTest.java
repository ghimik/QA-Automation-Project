package com.qa.project.ui.test;

import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrokenLinksImagesTest extends UnauthorizedSelenideTest {
    // @Test
    void shouldBeVisibleAndHaveCorrectHeader() {
        openElementsPage()
                .clickOnBrokenLinksImagesButton()
                .shouldBeVisible();
    }

    // @Test
    void shouldProperlyDisplayValidImage() {
        assertTrue(openElementsPage()
                        .clickOnBrokenLinksImagesButton()
                        .isValidImageDisplayed());

        assertNotNull(openElementsPage()
                .clickOnBrokenLinksImagesButton()
                .imageSection.shouldDisplayValidImage());

    }

    // @Test
    void shouldProperlyHandleBrokenImage() {

        openElementsPage()
                .clickOnBrokenLinksImagesButton()
                .imageSection.shouldDisplayBrokenImage();


    }
}
