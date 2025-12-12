package com.qa.project.api.bookstore.model;

import com.qa.project.api.bookstore.model.Book;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AllBooks{
    public ArrayList<Book> books;
}
