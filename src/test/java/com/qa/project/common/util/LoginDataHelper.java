package com.qa.project.common.util;

import com.qa.project.common.config.Config;
import com.qa.project.common.LoginData;

public final class LoginDataHelper {

    private LoginDataHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static LoginData demoUserLoginDataFromProperties() {
        return new LoginData(
                Config.testUsername(),
                Config.testPassword()
        );
    }
}
