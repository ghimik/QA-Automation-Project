package com.qa.project.common.util;

import com.qa.project.ui.components.ElementsPageLinksComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ResponseParser {

    private ResponseParser() {
        throw new IllegalStateException("Utility class");

    }

    public static ElementsPageLinksComponent.ApiLinkResponse parseResponse(String text) {
        final List<String> result = new ArrayList<>();
        final Pattern pattern = Pattern.compile("<b>(.*?)</b>");
        final Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result.add(matcher.group(1));
        }

        if (result.size() < 2) {
            throw new IllegalArgumentException(
                    "Не получилось распарсить ответ. Ожидалось минимум 2 значения в тегах <b>, получено: " + result.size()
            );
        }

        try {
            int code = Integer.parseInt(result.get(0));
            return new ElementsPageLinksComponent.ApiLinkResponse(code, result.get(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Первый параметр должен быть числом (код ответа), получено: " + result.get(0)
            );
        }
    }
}
