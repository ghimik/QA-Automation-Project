package com.qa.project.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.model.BookModel;
import org.openqa.selenium.By;

import java.awt.print.Book;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class BookStoreApplicationPage {

    private static final String booksList = "//div[@class=\"rt-tbody\"]";

    private static final String bookInTable = "div/div/div";

    private final SelenideElement loginButton = $(By.xpath("//li[./span[text()='Login']]"));

    private final SelenideElement searchTextBox = $(By.xpath("//input[@id='searchBox']"));

    public BookStoreApplicationPage() {
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public List<BookModel> searchBooks(String bookName) {
        searchTextBox.scrollIntoView(true).setValue(bookName);
        return $$x(booksList)
                .asDynamicIterable()
                .stream()
                .map(BookStoreApplicationPage::toModel)
                .toList();
    }

    private static BookModel toModel(SelenideElement element) {
        ElementsCollection items = element.$$x(bookInTable);
        final String title = items.get(1).text();
        final String author = items.get(2).text();
        final String publisher = items.get(3).text();
        return new BookModel(title, author, publisher);
    }
}
