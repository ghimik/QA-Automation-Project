package com.qa.project.ui.components;

import com.codeborne.selenide.SelenideElement;
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

        public SimpleTab dragAndDrop() {
            dragAndDropElement(draggable, droppable);
            return this;
        }

        public String getDroppableText() {
            return droppable.getText();
        }

        public boolean isDraggableInDroppable() {
            return droppable.$("#draggable").exists();
        }
    }

    public static class AcceptTab {
        private final SelenideElement acceptable = $("#acceptable");
        private final SelenideElement notAcceptable = $("#notAcceptable");
        private final SelenideElement droppable = $("#droppable");

        public AcceptTab dragAcceptable() {
            dragAndDropElementViaJS(acceptable, droppable);
            return this;
        }

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

        public PreventPropagationTab dragToNotGreedyInner() {
            dragAndDropElement(dragBox, notGreedyInner);
            return this;
        }

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

        public RevertableTab dragRevertable() {
            dragAndDropElement(revertable, droppable);
            return this;
        }

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

        public TabNavigator<SimpleTab> switchToSimpleTab() {
            parent.simpleTab.click();
            $("#simpleDropContainer").shouldBe(visible);
            return new TabNavigator<>(new SimpleTab(), parent);
        }

        public TabNavigator<AcceptTab> switchToAcceptTab() {
            parent.acceptTab.click();
            $("#acceptDropContainer").shouldBe(visible);
            return new TabNavigator<>(new AcceptTab(), parent);
        }

        public TabNavigator<PreventPropagationTab> switchToPreventPropagationTab() {
            parent.preventPropagationTab.click();
            $("#ppDropContainer").shouldBe(visible);
            return new TabNavigator<>(new PreventPropagationTab(), parent);
        }

        public TabNavigator<RevertableTab> switchToRevertableTab() {
            parent.revertableTab.click();
            $("#revertableDropContainer").shouldBe(visible);
            return new TabNavigator<>(new RevertableTab(), parent);
        }
    }

    public TabNavigator<SimpleTab> switchToSimpleTab() {
        simpleTab.click();
        $("#simpleDropContainer").shouldBe(visible);
        return new TabNavigator<>(new SimpleTab(), this);
    }

    public TabNavigator<AcceptTab> switchToAcceptTab() {
        acceptTab.click();
        $("#acceptDropContainer").shouldBe(visible);
        return new TabNavigator<>(new AcceptTab(), this);
    }

    public TabNavigator<PreventPropagationTab> switchToPreventPropagationTab() {
        preventPropagationTab.click();
        $("#ppDropContainer").shouldBe(visible);
        return new TabNavigator<>(new PreventPropagationTab(), this);
    }

    public TabNavigator<RevertableTab> switchToRevertableTab() {
        revertableTab.click();
        $("#revertableDropContainer").shouldBe(visible);
        return new TabNavigator<>(new RevertableTab(), this);
    }
}