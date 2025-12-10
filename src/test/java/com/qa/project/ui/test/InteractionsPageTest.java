package com.qa.project.ui.test;

import com.qa.project.ui.pages.InteractionsPage;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.MainPage.openMainPage;
import static org.hamcrest.MatcherAssert.assertThat;

public class InteractionsPageTest extends UnauthorizedSelenideTest {


    // @Test
    void testSimpleDragAndDrop() {
        final String droppableText = InteractionsPage.open()
                .clickDroppableButton()
                .switchToSimpleTab()
                .getTab()
                .dragAndDrop()
                .getDroppableText();


        assertThat(droppableText, Matchers.containsString("Dropped"));
    }


    // @Test
    void testAllTabsSwitchAndEmpty() {
        final InteractionsPage page = InteractionsPage.open();

        assertThat(page.clickDroppableButton()
                .switchToSimpleTab()
                .getTab().getDroppableText().toLowerCase(), Matchers.containsString("Drop Here".toLowerCase()));

        assertThat(page.clickDroppableButton()
                .switchToPreventPropagationTab()
                .getTab().getGreedyInnerText().toLowerCase(), Matchers.containsString("greedy"));

        assertThat(page.clickDroppableButton()
                .switchToPreventPropagationTab()
                .getTab().getNotGreedyInnerText().toLowerCase(), Matchers.containsString("not greedy"));

        assertThat(page.clickDroppableButton()
                .switchToRevertableTab()
                .getTab().getDroppableText().toLowerCase(), Matchers.containsString("Drop Here".toLowerCase()));

        assertThat(page.clickDroppableButton()
                .switchToAcceptTab()
                .getTab().getDroppableText().toLowerCase(), Matchers.containsString("Drop Here".toLowerCase()));

    }
}
