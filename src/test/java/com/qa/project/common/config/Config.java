package com.qa.project.common.config;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class Config {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = Config.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (is != null) {
                PROPERTIES.load(is);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    private Config() {
    }

    private static String get(String key) {
        String sys = System.getProperty(key);
        if (sys != null) return sys;

        String env = System.getenv(key.toUpperCase().replace('.', '_'));
        if (env != null) return env;

        return PROPERTIES.getProperty(key);
    }

    public static String baseUrl() {
        return Objects.requireNonNull(
                get("base.url"),
                "base.url in undefined"
        );
    }

    public static String testUsername() {
        return get("user.test.username");
    }

    public static String testPassword() {
        return get("user.test.password");
    }

    public static String reqresinBaseUrl() {
        return Objects.requireNonNull(
                get("reqresin.url"),
                "reqresin.url is undefined"
        );
    }

    public static String reqresinApiKey() {
        return Objects.requireNonNull(
                get("reqresin.api.key"),
                "reqresin.api.key is undefined"
        );
    }
}
