package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageLinksComponent;
import com.qa.project.ui.components.ElementsPageLinksComponent.ApiLinkResponse;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование раздела Elements")
@Feature("Links")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/links")
@Severity(SeverityLevel.CRITICAL)
public class ElementsPageLinksTest extends UnauthorizedSelenideTest {

    private static Stream<Object[]> apiLinksProvider() {
        return Stream.of(
                new Object[]{"Created", 201, "Created"},
                new Object[]{"No Content", 204, "No Content"},
                new Object[]{"Moved", 301, "Moved Permanently"},
                new Object[]{"Bad Request", 400, "Bad Request"},
                new Object[]{"Unauthorized", 401, "Unauthorized"},
                new Object[]{"Forbidden", 403, "Forbidden"},
                new Object[]{"Not Found", 404, "Not Found"}
        );
    }

    @ParameterizedTest()
    @MethodSource("apiLinksProvider")
    @Story("API ссылки с кодами ответа")
    @Description("Проверка, что API ссылки возвращают правильные HTTP коды и сообщения")
    void shouldReturnCorrectApiResponse(String linkName, int expectedCode, String expectedMessage) {
        Allure.parameter("Название ссылки", linkName);
        Allure.parameter("Ожидаемый HTTP код", expectedCode);
        Allure.parameter("Ожидаемое сообщение", expectedMessage);

        final ElementsPageLinksComponent.ApiLinksBranch branch = openElementsPage()
                .clickOnLinksButton()
                .clickOnApiLink(linkName);

        final ApiLinkResponse response = branch.getApiLinkResponse;

        assertEquals(expectedCode, response.getResponseCode(),
                String.format("HTTP код для ссылки '%s' должен быть %d", linkName, expectedCode));
        assertEquals(expectedMessage, response.getResponseMessage(),
                String.format("Сообщение для ссылки '%s' должно быть '%s'", linkName, expectedMessage));
    }

    @Test
    @Story("Простая ссылка")
    @Description("Проверка, что простая ссылка открывается в новой вкладке и можно вернуться обратно")
    void shouldOpenSimpleLinkInNewTabAndReturnBack() {
        final ElementsPageLinksComponent component = openElementsPage()
                .clickOnLinksButton();

        final ElementsPageLinksComponent.TabLinksBranch<?> branch = component.clickOnSimpleLink();

        assertEquals("MainPage", branch.getCurrentPageObject().getClass().getSimpleName(),
                "После клика на simple link должна открыться главная страница");

        branch.switchToPreviousTab();
    }

    @Test
    @Story("Динамическая ссылка")
    @Description("Проверка, что динамическая ссылка открывается в новой вкладке и можно вернуться обратно")
    void shouldOpenDynamicLinkInNewTabAndReturnBack() {
        final ElementsPageLinksComponent component = openElementsPage()
                .clickOnLinksButton();

        final ElementsPageLinksComponent.TabLinksBranch<?> branch = component.clickOnDynamicLink();

        assertEquals("MainPage", branch.getCurrentPageObject().getClass().getSimpleName(),
                "После клика на dynamic link должна открыться главная страница");

        branch.switchToPreviousTab();
    }
}