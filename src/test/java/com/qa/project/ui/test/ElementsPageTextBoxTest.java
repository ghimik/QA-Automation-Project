package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageTextBoxComponent;
import com.qa.project.ui.model.TextBoxData;
import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование раздела Elements")
@Feature("Text Box")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/text-box")
@Severity(SeverityLevel.CRITICAL)
public class ElementsPageTextBoxTest extends UnauthorizedSelenideTest {

    private static Stream<TextBoxData> textBoxDataProvider() {
        return Stream.of(
                new TextBoxData("Allure Jenkins", "allure@jenkins.com", "123 Groove St", "666 Groove St"),
                new TextBoxData("Герман", "kiril@example.ru", "ул. Германа, 10", "ул. Пушкина, д. Колотушкина"),
                new TextBoxData("张伟", "hzcheeto@example.ru", "北京市东城区", "上海市浦东新区"),
                new TextBoxData("👾🚀", "🦊@💌.ru", "🌍🗺️", "🏠🏢")
        );
    }

    @ParameterizedTest(name = "Данные: {0}")
    @MethodSource("textBoxDataProvider")
    @Story("Заполнение формы Text Box")
    @Description("Проверка корректного сохранения и отображения данных формы после отправки")
    void shouldSubmitFormAndReturnCorrectOutput(TextBoxData inputData) {
        Allure.parameter("Full Name", inputData.getFullName());
        Allure.parameter("Email", inputData.getEmail());
        Allure.parameter("Current Address", inputData.getCurrentAddress());
        Allure.parameter("Permanent Address", inputData.getPermanentAddress());

        final ElementsPageTextBoxComponent textBox = openElementsPage()
                .clickOnTextBoxButton()
                .fill(inputData)
                .submit();

        TextBoxData outputData = textBox.getOutputDivRawData();

        assertEquals(inputData.getFullName(), outputData.getFullName(),
                String.format("Full Name должно сохраниться как '%s'", inputData.getFullName()));
        assertEquals(inputData.getEmail(), outputData.getEmail(),
                String.format("Email должно сохраниться как '%s'", inputData.getEmail()));
        assertEquals(inputData.getCurrentAddress(), outputData.getCurrentAddress(),
                String.format("Current Address должно сохраниться как '%s'", inputData.getCurrentAddress()));
        assertEquals(inputData.getPermanentAddress(), outputData.getPermanentAddress(),
                String.format("Permanent Address должно сохраниться как '%s'", inputData.getPermanentAddress()));
    }
}