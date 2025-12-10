package com.qa.project.ui.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextBoxData {
    private String fullName;
    private String email;
    private String currentAddress;
    private String permanentAddress;

}

