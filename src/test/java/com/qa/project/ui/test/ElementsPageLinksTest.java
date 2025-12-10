package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageLinksComponent;
import com.qa.project.ui.components.ElementsPageLinksComponent.ApiLinkResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    // @ParameterizedTest
    @MethodSource("apiLinksProvider")
    void shouldReturnCorrectApiResponse(String linkName, int expectedCode, String expectedMessage) {
        final ElementsPageLinksComponent.ApiLinksBranch branch = openElementsPage()
                .clickOnLinksButton()
                .clickOnApiLink(linkName);

        final ApiLinkResponse response = branch.getApiLinkResponse;
        assertEquals(expectedCode, response.getResponseCode());
        assertEquals(expectedMessage, response.getResponseMessage());
    }

    // @Test
    void shouldOpenSimpleLinkInNewTabAndReturnBack() {
        final ElementsPageLinksComponent component = openElementsPage()
                .clickOnLinksButton();

        final ElementsPageLinksComponent.TabLinksBranch<?> branch = component.clickOnSimpleLink();
        assertEquals("MainPage", branch.getCurrentPageObject().getClass().getSimpleName());

        branch.switchToPreviousTab();
    }

    // @Test
    void shouldOpenDynamicLinkInNewTabAndReturnBack() {
        final ElementsPageLinksComponent component = openElementsPage()
                .clickOnLinksButton();

        final ElementsPageLinksComponent.TabLinksBranch<?> branch = component.clickOnDynamicLink();
        assertEquals("MainPage", branch.getCurrentPageObject().getClass().getSimpleName());

        branch.switchToPreviousTab();
    }
}
