package com.qa.project.api.reqresin.model;

import lombok.Data;

@Data
public class UserModel{
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}

