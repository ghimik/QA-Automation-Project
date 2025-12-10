package com.qa.project.common.util;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public final class UIUtil {

    private UIUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void dragAndDropElement(SelenideElement source, SelenideElement target) {
        new Actions(getWebDriver())
                .dragAndDrop(source, target)
                .perform();
    }

    public static void dragAndDropElementViaJS(SelenideElement source, SelenideElement target) {
        String js =
                "var source=arguments[0], target=arguments[1];" +
                "var dataTransfer = new DataTransfer();" +
                "source.dispatchEvent(new DragEvent('dragstart', {dataTransfer:dataTransfer}));" +
                "target.dispatchEvent(new DragEvent('dragover', {dataTransfer:dataTransfer}));" +
                "target.dispatchEvent(new DragEvent('drop', {dataTransfer:dataTransfer}));" +
                "source.dispatchEvent(new DragEvent('dragend', {dataTransfer:dataTransfer}));";
        executeJavaScript(js, source, target);
    }

}
