package com.qa.project.common.config;

import java.io.IOException;

public final class Properties {

    public static final String TEST_USER_USERNAME;
    public static final String TEST_USER_PASSWORD;
    public static final String BASE_URL;
    public static final String REQRESIN_API_KEY;
    public static final String REQRESIN_BASE_URL;



    static {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
            TEST_USER_USERNAME = System.getProperty("user.test.username");
            TEST_USER_PASSWORD = System.getProperty("user.test.password");
            BASE_URL = System.getProperty("base.url");
            REQRESIN_BASE_URL = System.getProperty("reqresin.url");
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("secret/keys.properties"));
            REQRESIN_API_KEY = System.getProperty("reqresin.api.key");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
