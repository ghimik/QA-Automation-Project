package com.qa.project.ui.test;

import com.qa.project.ui.pages.InteractionsPage;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.MainPage.openMainPage;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Тестирование раздела Interactions")
@Feature("Droppable")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/droppable")
@Severity(SeverityLevel.CRITICAL)
public class InteractionsPageTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Простой Drag and Drop")
    @Description("Проверка базового функционала перетаскивания элемента в Simple Tab")
    void testSimpleDragAndDrop() {
        final String droppableText = InteractionsPage.open()
                .clickDroppableButton()
                .switchToSimpleTab()
                .getTab()
                .dragAndDrop()
                .getDroppableText();

        assertThat("После drag & drop текст должен содержать 'Dropped'",
                droppableText, Matchers.containsString("Dropped"));
    }

    @Test
    @Story("Навигация по всем табам")
    @Description("Проверка, что все табы Droppable открываются и содержат правильные начальные тексты")
    void testAllTabsSwitchAndEmpty() {
        final InteractionsPage page = InteractionsPage.open();

        Allure.step("Проверка Simple Tab", () -> {
            String simpleText = page.clickDroppableButton()
                    .switchToSimpleTab()
                    .getTab()
                    .getDroppableText();
            assertThat("Simple Tab должен содержать 'Drop Here'",
                    simpleText.toLowerCase(), Matchers.containsString("drop here"));
        });

        Allure.step("Проверка Greedy в Prevent Propagation Tab", () -> {
            String greedyText = page.clickDroppableButton()
                    .switchToPreventPropagationTab()
                    .getTab()
                    .getGreedyInnerText();
            assertThat("Greedy элемент должен содержать 'greedy'",
                    greedyText.toLowerCase(), Matchers.containsString("greedy"));
        });

        Allure.step("Проверка Not Greedy в Prevent Propagation Tab", () -> {
            String notGreedyText = page.clickDroppableButton()
                    .switchToPreventPropagationTab()
                    .getTab()
                    .getNotGreedyInnerText();
            assertThat("Not Greedy элемент должен содержать 'not greedy'",
                    notGreedyText.toLowerCase(), Matchers.containsString("not greedy"));
        });

        Allure.step("Проверка Revertable Tab", () -> {
            String revertableText = page.clickDroppableButton()
                    .switchToRevertableTab()
                    .getTab()
                    .getDroppableText();
            assertThat("Revertable Tab должен содержать 'Drop Here'",
                    revertableText.toLowerCase(), Matchers.containsString("drop here"));
        });

        Allure.step("Проверка Accept Tab", () -> {
            String acceptText = page.clickDroppableButton()
                    .switchToAcceptTab()
                    .getTab()
                    .getDroppableText();
            assertThat("Accept Tab должен содержать 'Drop Here'",
                    acceptText.toLowerCase(), Matchers.containsString("drop here"));
        });
    }
}