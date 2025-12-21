package com.qa.project.ui.test;

import com.qa.project.ui.components.BrokenLinksImagesComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование раздела Elements")
@Feature("Broken Links - Images")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/broken")
@Severity(SeverityLevel.CRITICAL)
@Tag("ui")
@Tag("e2e")
public class BrokenLinksImagesTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Видимость компонента")
    @Description("Проверка, что компонент Broken Links - Images отображается с правильным заголовком")
    void shouldBeVisibleAndHaveCorrectHeader() {
        openElementsPage()
                .clickOnBrokenLinksImagesButton()
                .shouldBeVisible();
    }

    @Test
    @Story("Валидное изображение")
    @Description("Проверка, что валидное изображение корректно отображается на странице")
    void shouldProperlyDisplayValidImage() {
        BrokenLinksImagesComponent brokenLinksImagesComponent = openElementsPage()
                .clickOnBrokenLinksImagesButton();

        assertTrue(brokenLinksImagesComponent.isValidImageDisplayed(),
                "Валидное изображение должно отображаться");

        assertNotNull(brokenLinksImagesComponent.imageSection.shouldDisplayValidImage(),
                "Метод shouldDisplayValidImage должен вернуть объект ImageSection");
    }

    @Test
    @Story("Битое изображение")
    @Description("Проверка, что битое изображение корректно определяется как broken")
    void shouldProperlyHandleBrokenImage() {
        openElementsPage()
                .clickOnBrokenLinksImagesButton()
                .imageSection.shouldDisplayBrokenImage();
    }
}