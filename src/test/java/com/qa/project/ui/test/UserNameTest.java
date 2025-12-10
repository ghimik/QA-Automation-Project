package com.qa.project.ui.test;

import com.qa.project.common.config.Properties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserNameTest extends AuthorizedSelenideTest{

    // @Test
    public void checkIfUserNameInLabelMatchesWithLogin() {
        assertTrue(
        authorizeManually()
                .clickOnGoToBookStoreButton()
                .getUserName()
                .contentEquals(Properties.TEST_USER_USERNAME)
        );
    }
}
