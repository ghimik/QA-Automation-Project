package com.qa.project.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FramesComponent {

    private final SelenideElement frame1 = $("#frame1");

    public FramesComponent switchToFrame1() {
        Selenide.switchTo().frame(frame1);
        return this;
    }

    public FramesComponent switchToChildFrame() {
        Selenide.switchTo().frame(frame1);
        Selenide.switchTo().frame(0);
        return this;
    }

    public FramesComponent switchToParentFrame() {
        Selenide.switchTo().parentFrame();
        return this;
    }

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