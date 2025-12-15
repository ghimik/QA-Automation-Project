package com.qa.project.ui.test;

import com.qa.project.ui.components.PractiseFormComponent;
import com.qa.project.ui.model.SubmissionModalData;
import com.qa.project.ui.model.UserFormModel;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.qa.project.ui.pages.FormsPage.openFormsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование раздела Forms")
@Feature("Practice Form")
@Owner("alexey")
@Link(name = "Ссылка на форму", url = "https://demoqa.com/automation-practice-form")
@Severity(SeverityLevel.BLOCKER)
public class FormTest extends UnauthorizedSelenideTest{


    @Test
    @Story("Полное заполнение формы")
    @Description("Проверка, что все данные формы корректно сохраняются и отображаются в модальном окне после отправки")
    @SuppressWarnings("unchecked")
    public void testSubmittedFormEqualsResultForm() {

        HashMap<String, Object> data = new HashMap<>();
        data.put("username", "Jenkins Jenkins");
        data.put("email", "jenkins.jenkins@jenkins.com");
        data.put("gender", UserFormModel.Gender.MALE);
        data.put("mobile", "8005553535");
        data.put("birthDate", LocalDate.of(1995, 5, 23));
        data.put("subjects", List.of("Maths", "English"));
        data.put("hobbies", Set.of(UserFormModel.Hobby.SPORTS, UserFormModel.Hobby.MUSIC));
        data.put("address", "123 Lenina Street");
        data.put("state", UserFormModel.States.RAJASTHAN);
        data.put("city", UserFormModel.City.JAIPUR);

        // в силу того, что эти данные заполняются в блоке выше, и нигде больше, явный каст object безопасен
        UserFormModel user = new UserFormModel();
        user.setUsername((String) data.get("username"));
        user.setEmail((String) data.get("email"));
        user.setGender((UserFormModel.Gender) data.get("gender"));
        user.setMobileNumber((String) data.get("mobile"));
        user.setBirthDate((LocalDate) data.get("birthDate"));
        user.setSubjects((List<String>) data.get("subjects"));
        user.setHobbies((Set<UserFormModel.Hobby>) data.get("hobbies"));
        user.setCurrentAddress((String) data.get("address"));
        user.setState((UserFormModel.States) data.get("state"));
        user.setCity((UserFormModel.City) data.get("city"));


         SubmissionModalData submittedData = openFormsPage()
                .clickOnPractiseFormButton()
                .fillForm(user)
                .submitForm()
                 .extractData();

         assertEquals(data.get("username"), submittedData.getUserName());
         assertEquals(data.get("email"), submittedData.getEmail());
         assertEquals(data.get("gender"), submittedData.getGender());
         assertEquals(data.get("mobile"), submittedData.getMobile());
         assertEquals(data.get("birthDate"), submittedData.getDateOfBirth());
        assertEquals(data.get("address"), submittedData.getAddress());
        assertEquals(data.get("state"), submittedData.getState());
        assertEquals(data.get("city"), submittedData.getCity());

        List<String> expectedSubjects = (List<String>) data.get("subjects");
        List<String> actualSubjects = submittedData.getSubjects();

        assertThat(actualSubjects, Matchers.containsInAnyOrder(
                expectedSubjects.toArray(new String[0])
        ));

        Set<UserFormModel.Hobby> expectedHobbies = (Set<UserFormModel.Hobby>) data.get("hobbies");
        Set<UserFormModel.Hobby> actualHobbies = submittedData.getHobbies();

        assertThat(actualHobbies, Matchers.containsInAnyOrder(
                expectedHobbies.toArray(new UserFormModel.Hobby[0])
        ));



    }


}
