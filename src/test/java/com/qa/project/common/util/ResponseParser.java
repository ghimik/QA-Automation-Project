package com.qa.project.common.util;

import com.qa.project.ui.components.ElementsPageLinksComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ResponseParser {

    private ResponseParser() {
        throw new IllegalStateException("Utility class");
    }

    public static ElementsPageLinksComponent.ApiLinkResponse parseResponse(String text) {
        final Pattern pattern = Pattern.compile("staus\\s*(\\d{3})\\s*and status text\\s*(.*)", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            int code;
            try {
                code = Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Не удалось распарсить код ответа из строки: " + matcher.group(1)
                );
            }
            String message = matcher.group(2).trim();
            return new ElementsPageLinksComponent.ApiLinkResponse(code, message);
        }

        throw new IllegalArgumentException("Не удалось распарсить ответ: " + text);
    }
}
