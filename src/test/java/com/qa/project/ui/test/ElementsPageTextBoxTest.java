package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageTextBoxComponent;
import com.qa.project.ui.model.TextBoxData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementsPageTextBoxTest extends UnauthorizedSelenideTest {

    private static Stream<TextBoxData> textBoxDataProvider() {
        return Stream.of(
                new TextBoxData("Allure Jenkins", "allure@jenkins.com", "123 Groove St", "666 Groove St"),
                new TextBoxData("Герман", "kiril@example.ru", "ул. Германа, 10", "ул. Пушкина, д. Колотушкина"),
                new TextBoxData("张伟", "hzcheeto@example.ru", "北京市东城区", "上海市浦东新区"),
                new TextBoxData("👾🚀", "🦊@💌.ru", "🌍🗺️", "🏠🏢")
        );
    }

    // @ParameterizedTest
    @MethodSource("textBoxDataProvider")
    void shouldSubmitFormAndReturnCorrectOutput(TextBoxData inputData) {
        final ElementsPageTextBoxComponent textBox = openElementsPage()
                .clickOnTextBoxButton()
                .fill(inputData)
                .submit();

        assertEquals(inputData.getFullName(), textBox.getOutputDivRawData().getFullName());
        assertEquals(inputData.getEmail(), textBox.getOutputDivRawData().getEmail());
        assertEquals(inputData.getCurrentAddress(), textBox.getOutputDivRawData().getCurrentAddress());
        assertEquals(inputData.getPermanentAddress(), textBox.getOutputDivRawData().getPermanentAddress());
    }
}
