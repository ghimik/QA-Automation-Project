package com.qa.project.common.util;

import com.qa.project.ui.model.UserFormModel;

import java.util.Objects;

public final class StringHelper {

    private StringHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String toLowerCaseFirstLetterUpperCase(String old) {
        Objects.requireNonNull(old);
        StringBuilder sb = new StringBuilder(old.toLowerCase());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String stateToDropdown(UserFormModel.States state) {
        return switch (state) {
            case NCR -> "NCR";
            case UTTAR_PRADESH -> "Uttar Pradesh";
            case HARYANA -> "Haryana";
            case RAJASTHAN -> "Rajasthan";
        };
    }

}
