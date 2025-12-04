package com.qa.project.common.util;

import com.qa.project.common.config.Properties;
import com.qa.project.ui.model.LoginFormData;

public final class LoginDataHelper {

    private LoginDataHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static LoginFormData demoUserLoginDataFromProperties() {
        return new LoginFormData(
                Properties.TEST_USER_USERNAME,
                Properties.TEST_USER_PASSWORD
        );
    }
}
