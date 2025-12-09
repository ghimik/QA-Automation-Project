package com.qa.project.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.model.SubmissionModalData;
import com.qa.project.ui.model.UserFormModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

public class SubmissionModalComponent {

    private final SelenideElement modalContent = $(".modal-content");
    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement modalBody = $(".modal-body");
    private final SelenideElement closeButton = $("#closeLargeModal");
    private final SelenideElement modalTable = $(".table-responsive table");

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

    public SubmissionModalData extractData() {
        waitForModal();

        Map<String, Object> rawData = extractRawData();

        return SubmissionModalData.builder()
                .userName(parseUserName((String) rawData.get("Student Name")))
                .email(parseEmail((String) rawData.get("Student Email")))
                .gender(parseGender((String) rawData.get("Gender")))
                .mobile(parseMobile((String) rawData.get("Mobile")))
                .dateOfBirth(parseDateOfBirth((String) rawData.get("Date of Birth")))
                .subjects(parseSubjects((String) rawData.get("Subjects")))
                .hobbies(parseHobbies((String) rawData.get("Hobbies")))
                .picture((String)rawData.get("Picture"))
                .address(parseAddress((String) rawData.get("Address")))
                .state(parseState((String) rawData.get("State and City")))
                .city(parseCity((String) rawData.get("State and City")))
                .build();
    }

    private Map<String, Object> extractRawData() {
        Map<String, Object> data = new HashMap<>();

        modalTable.$$("tbody tr").forEach(row -> {
            String label = row.$("td:nth-child(1)").getText().trim();
            String value = row.$("td:nth-child(2)").getText().trim();
            data.put(label, value);
        });

        return data;
    }


    private String parseUserName(String rawValue) {
        return rawValue != null ? rawValue.trim() : null;
    }

    private String parseEmail(String rawValue) {
        return rawValue != null ? rawValue.trim() : null;
    }

    private UserFormModel.Gender parseGender(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return null;
        }

        return UserFormModel.Gender.valueOf(rawValue.trim().toUpperCase());

    }

    private String parseMobile(String rawValue) {
        return rawValue != null ? rawValue.trim() : null;
    }

    private LocalDate parseDateOfBirth(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return null;
        }

        try {
            String cleanedDate = rawValue.trim();
            return LocalDate.parse(cleanedDate, DATE_FORMATTER);
        } catch (Exception e) {
            String[] possibleFormats = {
                    "dd MMMM,yyyy",
                    "dd MMM,yyyy",
                    "dd/MM/yyyy",
                    "yyyy-MM-dd"
            };

            for (String format : possibleFormats) {
                try {
                    return LocalDate.parse(rawValue.trim(),
                            DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
                } catch (Exception ignored) {}
            }

            throw new IllegalArgumentException("Cannot parse date: " + rawValue, e);
        }
    }

    private List<String> parseSubjects(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(rawValue.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private Set<UserFormModel.Hobby> parseHobbies(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return Collections.emptySet();
        }

        Set<UserFormModel.Hobby> hobbies = new HashSet<>();
        String[] hobbyParts = rawValue.split(",");

        for (String hobbyStr : hobbyParts) {
            String hobbyTrimmedUpperCase = hobbyStr.trim().toUpperCase();
            hobbies.add(UserFormModel.Hobby.valueOf(hobbyTrimmedUpperCase));

        }

        return hobbies;
    }



    private String parseAddress(String rawValue) {
        return rawValue != null ? rawValue.trim() : null;
    }

    private UserFormModel.States parseState(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return null;
        }

        String[] parts = rawValue.split("\\s+");
        if (parts.length == 0) {
            return null;
        }

        String stateStr = parts[0].trim().toUpperCase().replace(" ", "_");

        try {
            return UserFormModel.States.valueOf(stateStr);
        } catch (IllegalArgumentException e) {
            for (UserFormModel.States state : UserFormModel.States.values()) {
                if (state.name().equalsIgnoreCase(stateStr)) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Unknown state: " + stateStr);
        }
    }

    private UserFormModel.City parseCity(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return null;
        }

        String[] parts = rawValue.split("\\s+");
        if (parts.length < 2) {
            return null;
        }

        String cityStr = parts[1].trim().toUpperCase();

        try {
            return UserFormModel.City.valueOf(cityStr);
        } catch (IllegalArgumentException e) {
            for (UserFormModel.City city : UserFormModel.City.values()) {
                if (city.name().equalsIgnoreCase(cityStr)) {
                    return city;
                }
            }
            throw new IllegalArgumentException("Unknown city: " + cityStr);
        }
    }

    private void waitForModal() {
        modalContent.shouldBe(Condition.visible);
        modalTitle.shouldHave(Condition.text("Thanks for submitting the form"));
    }

}