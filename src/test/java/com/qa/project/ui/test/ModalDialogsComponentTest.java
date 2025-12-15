package com.qa.project.ui.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.qa.project.ui.pages.AlertsFramesWindowsPage.openAlertsFramesWindowsPage;

@Epic("Тестирование раздела Alerts, Frame & Windows")
@Feature("Modal Dialogs")
@Owner("alexey")
@Link(name = "Ссылка на раздел", url = "https://demoqa.com/modal-dialogs")
@Severity(SeverityLevel.CRITICAL)
public class ModalDialogsComponentTest extends UnauthorizedSelenideTest {

    @Test
    @Story("Загрузка компонента")
    @Description("Проверка, что компонент Modal Dialogs корректно загружается и отображается")
    void testComponentLoads() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .verifyComponentLoaded();
    }

    @Test
    @Story("Открытие Small Modal")
    @Description("Проверка открытия малого модального окна по кнопке")
    void testSmallModalOpens() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .verifyOpened();
    }

    @Test
    @Story("Открытие Large Modal")
    @Description("Проверка открытия большого модального окна по кнопке")
    void testLargeModalOpens() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openLargeModal()
                .verifyOpened();
    }

    @Test
    @Story("Закрытие Small Modal")
    @Description("Проверка закрытия малого модального окна кнопкой Close")
    void testSmallModalCloses() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .close();
    }

    @Test
    @Story("Закрытие Large Modal")
    @Description("Проверка закрытия большого модального окна кнопкой Close")
    void testLargeModalCloses() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openLargeModal()
                .close();
    }

    @Test
    @Story("Закрытие модального окна крестиком")
    @Description("Проверка закрытия модального окна кликом на крестик (X)")
    void testModalClosesWithX() {
        openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .closeWithX();
    }

    @Test
    @Story("Содержимое модального окна")
    @Description("Проверка, что модальное окно содержит непустой текст")
    void testModalTextContent() {
        String bodyText = openAlertsFramesWindowsPage()
                .clickOnModalDialogsButton()
                .openSmallModal()
                .getBodyText();

        Assertions.assertFalse(bodyText.isEmpty(),
                "Текст в модальном окне не должен быть пустым");
    }
}