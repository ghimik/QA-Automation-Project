package com.qa.project.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class FramesComponent {

    private final SelenideElement frame1 = $("#frame1");

    @Step("Переключиться на Frame 1")
    public FramesComponent switchToFrame1() {
        Selenide.switchTo().frame(frame1);
        return this;
    }

    @Step("Переключиться на Child Frame (вложенный)")
    public FramesComponent switchToChildFrame() {
        Selenide.switchTo().frame(frame1);
        Selenide.switchTo().frame(0);
        return this;
    }

    @Step("Переключиться на Parent Frame")
    public FramesComponent switchToParentFrame() {
        Selenide.switchTo().parentFrame();
        return this;
    }

    @Step("Переключиться на Основной контент")
    public FramesComponent switchToDefaultContent() {
        Selenide.switchTo().defaultContent();
        return this;
    }

    public String getCurrentFrameText() {
        return Selenide.$("body").getText();
    }

    public String getChildFrameText() {
        return Selenide.$("p").getText();
    }
}