package com.qa.project.api.reqresin.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ReqresinUsersResponse {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private ArrayList<UserModel> data;
}


