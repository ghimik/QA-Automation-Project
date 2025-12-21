package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.qa.project.common.util.UIUtil.dragAndDropElement;
import static com.qa.project.common.util.UIUtil.dragAndDropElementViaJS;

public class DroppableInteractionsComponent {

    private final SelenideElement root = $("#droppableContainer");
    private final SelenideElement simpleTab = $("[data-rb-event-key='simple']");
    private final SelenideElement acceptTab = $("[data-rb-event-key='accept']");
    private final SelenideElement preventPropagationTab = $("[data-rb-event-key='preventPropogation']");
    private final SelenideElement revertableTab = $("[data-rb-event-key='revertable']");

    public static class SimpleTab {
        private final SelenideElement draggable = $("#draggable");
        private final SelenideElement droppable = $("#droppable");

        @Step("Перетащить элемент в Simple Tab")
        public SimpleTab dragAndDrop() {
            dragAndDropElement(draggable, droppable);
            return this;
        }

        @Step("Получить текст droppable элемента в Simple Tab")
        public String getDroppableText() {
            return droppable.getText();
        }

        @Step("Проверить, что draggable находится внутри droppable (Simple Tab)")
        public boolean isDraggableInDroppable() {
            return droppable.$("#draggable").exists();
        }
    }

    public static class AcceptTab {
        private final SelenideElement acceptable = $("#acceptable");
        private final SelenideElement notAcceptable = $("#notAcceptable");
        private final SelenideElement droppable = $("#droppable");

        @Step("Перетащить acceptable элемент в Accept Tab (через JS)")
        public AcceptTab dragAcceptable() {
            dragAndDropElementViaJS(acceptable, droppable);
            return this;
        }

        @Step("Перетащить not acceptable элемент в Accept Tab")
        public AcceptTab dragNotAcceptable() {
            dragAndDropElement(notAcceptable, droppable);
            return this;
        }

        public String getDroppableText() {
            return droppable.getText();
        }
    }

    public static class PreventPropagationTab {
        private final SelenideElement dragBox = $("#dragBox");
        private final SelenideElement notGreedyInner = $("#notGreedyInnerDropBox");
        private final SelenideElement greedyInner = $("#greedyDropBoxInner");

        @Step("Перетащить элемент в not greedy inner (Prevent Propagation Tab)")
        public PreventPropagationTab dragToNotGreedyInner() {
            dragAndDropElement(dragBox, notGreedyInner);
            return this;
        }

        @Step("Перетащить элемент в greedy inner (Prevent Propagation Tab)")
        public PreventPropagationTab dragToGreedyInner() {
            dragAndDropElement(dragBox, greedyInner);
            return this;
        }

        public String getGreedyInnerText() {
            return greedyInner.getText();
        }

        public String getNotGreedyInnerText() {
            return notGreedyInner.getText();
        }
    }

    public static class RevertableTab {
        private final SelenideElement revertable = $("#revertable");
        private final SelenideElement notRevertable = $("#notRevertable");
        private final SelenideElement droppable = $("#droppable");

        @Step("Перетащить revertable элемент в Revertable Tab")
        public RevertableTab dragRevertable() {
            dragAndDropElement(revertable, droppable);
            return this;
        }

        @Step("Перетащить not revertable элемент в Revertable Tab")
        public RevertableTab dragNotRevertable() {
            dragAndDropElement(notRevertable, droppable);
            return this;
        }

        public String getDroppableText() {
            return droppable.getText();
        }
    }

    @RequiredArgsConstructor
    public static class TabNavigator<T> {
        @Getter
        private final T tab;
        private final DroppableInteractionsComponent parent;

        @Step("Переключиться на Simple Tab")
        public TabNavigator<SimpleTab> switchToSimpleTab() {
            parent.simpleTab.click();
            $("#simpleDropContainer").shouldBe(visible);
            return new TabNavigator<>(new SimpleTab(), parent);
        }

        @Step("Переключиться на Accept Tab")
        public TabNavigator<AcceptTab> switchToAcceptTab() {
            parent.acceptTab.click();
            $("#acceptDropContainer").shouldBe(visible);
            return new TabNavigator<>(new AcceptTab(), parent);
        }

        @Step("Переключиться на Prevent Propagation Tab")
        public TabNavigator<PreventPropagationTab> switchToPreventPropagationTab() {
            parent.preventPropagationTab.click();
            $("#ppDropContainer").shouldBe(visible);
            return new TabNavigator<>(new PreventPropagationTab(), parent);
        }

        @Step("Переключиться на Revertable Tab")
        public TabNavigator<RevertableTab> switchToRevertableTab() {
            parent.revertableTab.click();
            $("#revertableDropContainer").shouldBe(visible);
            return new TabNavigator<>(new RevertableTab(), parent);
        }
    }

    @Step("Переключиться на Simple Tab")
    public TabNavigator<SimpleTab> switchToSimpleTab() {
        simpleTab.click();
        $("#simpleDropContainer").shouldBe(visible);
        return new TabNavigator<>(new SimpleTab(), this);
    }

    @Step("Переключиться на Accept Tab")
    public TabNavigator<AcceptTab> switchToAcceptTab() {
        acceptTab.click();
        $("#acceptDropContainer").shouldBe(visible);
        return new TabNavigator<>(new AcceptTab(), this);
    }

    @Step("Переключиться на Prevent Propagation Tab")
    public TabNavigator<PreventPropagationTab> switchToPreventPropagationTab() {
        preventPropagationTab.click();
        $("#ppDropContainer").shouldBe(visible);
        return new TabNavigator<>(new PreventPropagationTab(), this);
    }

    @Step("Переключиться на Revertable Tab")
    public TabNavigator<RevertableTab> switchToRevertableTab() {
        revertableTab.click();
        $("#revertableDropContainer").shouldBe(visible);
        return new TabNavigator<>(new RevertableTab(), this);
    }
}