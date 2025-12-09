package com.qa.project.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionModalData {
    private String userName;
    private String email;
    private UserFormModel.Gender gender;
    private String mobile;
    private LocalDate dateOfBirth;
    private List<String> subjects;
    private Set<UserFormModel.Hobby> hobbies;
    private String picture;
    private String address;
    private UserFormModel.States state;
    private UserFormModel.City city;
}