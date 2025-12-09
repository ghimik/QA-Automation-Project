package com.qa.project.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.common.util.StringHelper;
import com.qa.project.ui.model.UserFormModel;
import com.qa.project.ui.model.UserFormModel.City;
import com.qa.project.ui.model.UserFormModel.Gender;
import com.qa.project.ui.model.UserFormModel.Hobby;
import com.qa.project.ui.model.UserFormModel.States;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;

public class PractiseFormComponent {

    private final String formDivs = "//form[@id='userForm']/div";

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement mobileInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement currentAddressTextarea = $("#currentAddress");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement submitButton = $("#submit");

    private final By genderRadios = By.cssSelector("input[name='gender']");
    private final By hobbiesCheckboxes = By.cssSelector("input[type='checkbox'][id^='hobbies-checkbox-']");

    private final SelenideElement stateDropdown = $("#state");
    private final SelenideElement cityDropdown = $("#city");


    public PractiseFormComponent setFirstName(String firstName) {
        firstNameInput.shouldBe(Condition.visible).setValue(firstName).doubleClick();
        return this;
    }

    public PractiseFormComponent setLastName(String lastName) {
        lastNameInput.shouldBe(Condition.visible).setValue(lastName);
        return this;
    }

    public PractiseFormComponent setEmail(String email) {
        emailInput.shouldBe(Condition.visible).setValue(email);
        return this;
    }

    public PractiseFormComponent setGender(Gender gender) {
        $(getGenderCheckBoxLocator(gender))
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public PractiseFormComponent setMobileNumber(String mobileNumber) {
        mobileInput.shouldBe(Condition.visible).setValue(mobileNumber);
        return this;
    }

    /*
     * этот Datepicker - настоящий памятник фронтенд-инженерии.
     * 1. clear() не работает - игнорирует вызов.
     * 2. setValue() поверх существующего значения?
     *    он просто дописывает новое к старому, получая абракадабру типа "09 Dec 202409 Dec 2025".
     * 3. executeJavaScript()? увы, React никаким образом не реагирует.
     * 4. попытка стереть вручную - страница реагирует как на покушение на мировую безопасность.
     *
     * стандартные методы Selenide здесь бессильны.
     * вот единственный рабочий способ - через интерфейс календаря
     */

    public PractiseFormComponent setDateOfBirth(LocalDate date) {
        dateOfBirthInput.click();

        SelenideElement datePicker = $(".react-datepicker");
        datePicker.shouldBe(Condition.visible);

        SelenideElement yearSelect = $(".react-datepicker__year-select");
        if (yearSelect.exists()) {
            yearSelect.selectOption(String.valueOf(date.getYear()));
        }

        SelenideElement monthSelect = $(".react-datepicker__month-select");
        if (monthSelect.exists()) {
            monthSelect.selectOption(date.getMonth().getDisplayName(
                    TextStyle.FULL_STANDALONE, Locale.ENGLISH));
        }

        String dayClass = String.format(".react-datepicker__day--%03d:not(.react-datepicker__day--outside-month)",
                date.getDayOfMonth());
        $(dayClass).click();

        return this;
    }

    public PractiseFormComponent setSubjects(List<String> subjects) {
        for (String subject : subjects) {
            subjectsInput.shouldBe(Condition.visible).setValue(subject).pressEnter();
        }
        return this;
    }

    public PractiseFormComponent setHobbies(Set<Hobby> hobbies) {
        $$(hobbiesCheckboxes).forEach(checkbox -> {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        });

        for (Hobby hobby : hobbies) {
            String labelXPath = getHobbyLabelXPath(hobby);
            $(By.xpath(labelXPath)).shouldBe(Condition.visible).click();
        }
        return this;
    }

    private String getHobbyLabelXPath(Hobby hobby) {
        return switch (hobby) {
            case SPORTS -> "//input[@id='hobbies-checkbox-1']/../label";
            case READING -> "//input[@id='hobbies-checkbox-2']/../label";
            case MUSIC -> "//input[@id='hobbies-checkbox-3']/../label";
        };
    }

    public PractiseFormComponent uploadPicture(File file) {
        uploadPictureInput.shouldBe(Condition.visible).uploadFile(file);
        return this;
    }

    public PractiseFormComponent setCurrentAddress(String address) {
        currentAddressTextarea.shouldBe(Condition.visible).setValue(address);
        return this;
    }

    public PractiseFormComponent setState(States state) {
        stateDropdown.shouldBe(Condition.visible).click();
        $(By.xpath("//div[contains(@class, 'menu')]//div[text()='" + StringHelper.stateToDropdown(state) + "']"))
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public PractiseFormComponent setCity(City city) {
        cityDropdown.shouldBe(Condition.visible).click();
        $(By.xpath("//div[contains(@class, 'menu')]//div[text()='" + StringHelper.toLowerCaseFirstLetterUpperCase(city.name()) + "']"))
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public SubmissionModalComponent submitForm() {
        submitButton.shouldBe(Condition.enabled).click();
        return new SubmissionModalComponent();
    }

    public PractiseFormComponent fillForm(UserFormModel model) {
        setFirstName(model.getUsername().split(" ")[0]);
        setLastName(model.getUsername().split(" ").length > 1 ? model.getUsername().split(" ")[1] : "");
        setEmail(model.getEmail());
        setGender(model.getGender());
        setMobileNumber(model.getMobileNumber());
        if (model.getBirthDate() != null)
            setDateOfBirth(model.getBirthDate());
        setHobbies(model.getHobbies());
        setSubjects(model.getSubjects());

        setCurrentAddress(model.getCurrentAddress());

        if (model.getState() != null) {
            setState(model.getState());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (model.getCity() != null) {
                setCity(model.getCity());
            }
        }
        return this;
    }


    public void clearForm() {
        firstNameInput.clear();
        lastNameInput.clear();
        emailInput.clear();
        mobileInput.clear();
        dateOfBirthInput.clear();
        subjectsInput.clear();
        currentAddressTextarea.clear();

        $$(genderRadios).forEach(radio -> {
            if (radio.isSelected()) {
                radio.click();
            }
        });

        $$(hobbiesCheckboxes).forEach(checkbox -> {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        });
    }

    private By getGenderCheckBoxLocator(Gender gender) {
        return By.xpath("//input[@name='gender'][@value='"
                + StringHelper.toLowerCaseFirstLetterUpperCase(gender.name()) + "']/../label");
    }

}