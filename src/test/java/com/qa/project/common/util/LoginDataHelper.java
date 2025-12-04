package com.qa.project.common.util;

import com.qa.project.common.config.Properties;
import com.qa.project.common.LoginData;

public final class LoginDataHelper {

    private LoginDataHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static LoginData demoUserLoginDataFromProperties() {
        return new LoginData(
                Properties.TEST_USER_USERNAME,
                Properties.TEST_USER_PASSWORD
        );
    }
}
