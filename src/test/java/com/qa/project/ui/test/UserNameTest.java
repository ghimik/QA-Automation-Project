package com.qa.project.ui.test;

import com.qa.project.common.config.Properties;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование авторизации")
@Feature("Отображение имени пользователя")
@Owner("alexey")
@Link(name = "Ссылка на профиль", url = "https://demoqa.com/profile")
@Severity(SeverityLevel.BLOCKER)
public class UserNameTest extends AuthorizedSelenideTest {

    @Test
    @Story("Имя пользователя после авторизации")
    @Description("Проверка что после авторизации в интерфейсе отображается корректное имя пользователя")
    public void checkIfUserNameInLabelMatchesWithLogin() {
        Allure.parameter("Ожидаемое имя пользователя", Properties.TEST_USER_USERNAME);

        String actualUserName = authorizeManually()
                .clickOnGoToBookStoreButton()
                .getUserName();

        assertTrue(
                actualUserName.contentEquals(Properties.TEST_USER_USERNAME),
                String.format("Имя пользователя должно быть '%s'; получено: '%s'",
                        Properties.TEST_USER_USERNAME, actualUserName)
        );
    }
}