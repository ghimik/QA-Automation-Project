package com.qa.project.ui.test;

import com.qa.project.ui.components.ElementsPageRadioButtonComponent;
import com.qa.project.ui.components.ElementsPageRadioButtonComponent.Option;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.ElementsPage.openElementsPage;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование раздела Elements")
@Feature("Radio Button")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/radio-button")
@Severity(SeverityLevel.CRITICAL)
public class ElementsPageRadioButtonTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Выбор опции 'Yes'")
    @Description("Проверка, что радио-кнопка 'Yes' корректно выбирается и отображает правильное значение")
    void shouldSelectYesOption() {
        final ElementsPageRadioButtonComponent radio = openElementsPage()
                .clickOnRadioButtonButton()
                .select(Option.YES);

        assertTrue(radio.isOptionSelected(Option.YES),
                "Опция 'Yes' должна быть выбрана после клика");
        assertTrue(radio.isSelectedValueCorrect(Option.YES),
                "Отображаемое значение должно соответствовать выбранной опции 'Yes'");
    }

    @Test
    @Story("Выбор опции 'Impressive'")
    @Description("Проверка, что радио-кнопка 'Impressive' корректно выбирается и отображает правильное значение")
    void shouldSelectImpressiveOption() {
        final ElementsPageRadioButtonComponent radio = openElementsPage()
                .clickOnRadioButtonButton()
                .select(Option.IMPRESSIVE);

        assertTrue(radio.isOptionSelected(Option.IMPRESSIVE),
                "Опция 'Impressive' должна быть выбрана после клика");
        assertTrue(radio.isSelectedValueCorrect(Option.IMPRESSIVE),
                "Отображаемое значение должно соответствовать выбранной опции 'Impressive'");
    }

    @Test
    @Story("Проверка disabled опции 'No'")
    @Description("Проверка, что радио-кнопка 'No' недоступна для выбора (disabled)")
    void shouldHaveNoOptionDisabled() {
        final ElementsPageRadioButtonComponent radio = openElementsPage()
                .clickOnRadioButtonButton();

        assertTrue(radio.isNoOptionDisabled(),
                "Опция 'No' должна быть disabled по умолчанию");

        radio.select(Option.NO);
        assertFalse(radio.isOptionSelected(Option.NO),
                "Опция 'No' не должна выбираться, даже при попытке клика (disabled)");
    }
}