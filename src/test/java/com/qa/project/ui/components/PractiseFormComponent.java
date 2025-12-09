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

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    public PractiseFormComponent setDateOfBirth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").localizedBy(Locale.ENGLISH);
        String formattedDate = date.format(formatter);
        dateOfBirthInput.shouldBe(Condition.visible).setValue(formattedDate).pressEnter();
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