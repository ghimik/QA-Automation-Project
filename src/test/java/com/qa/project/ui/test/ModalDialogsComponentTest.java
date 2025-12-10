package com.qa.project.ui.test;

import com.qa.project.ui.components.ModalDialogsComponent;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.AlertsFramesWindowsPage.openAlertsFramesWindowsPage;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModalDialogsComponentTest extends UnauthorizedSelenideTest {

    @Test
    void testComponentLoads() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .verifyComponentLoaded();
    }

    @Test
    void testSmallModalOpens() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .verifyOpened();
    }

    @Test
    void testLargeModalOpens() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openLargeModal()
                .verifyOpened();
    }

    @Test
    void testSmallModalCloses() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .close();
    }

    @Test
    void testLargeModalCloses() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openLargeModal()
                .close();
    }

    @Test
    void testModalClosesWithX() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .closeWithX();
    }

    @Test
    void testModalTextContent() {
        String bodyText = openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .getBodyText();

        Assertions.assertFalse(bodyText.isEmpty());
    }

}
