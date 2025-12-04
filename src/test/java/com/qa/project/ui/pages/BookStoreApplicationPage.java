package com.qa.project.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.qa.project.ui.components.LoginFormComponent;
import com.qa.project.ui.model.BookModel;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BookStoreApplicationPage {

    private static final String booksList = "//div[@class=\"rt-tbody\"]";

    private static final String bookInTable = "div/div/div";

    private static final String userNameLabel = "//label[@id='userName-value']";

    private final SelenideElement loginButton = $(By.xpath("//li[./span[text()='Login']]"));

    private final SelenideElement searchTextBox = $(By.xpath("//input[@id='searchBox']"));

    public BookStoreApplicationPage() {
    }

    public LoginFormComponent clickOnLoginButton() {
        loginButton.click();
        return new LoginFormComponent();
    }

    public List<BookModel> searchBooks(String bookName) {
        searchTextBox.scrollIntoView(true).setValue(bookName);
        return $$x(booksList)
                .asDynamicIterable()
                .stream()
                .map(BookStoreApplicationPage::toModel)
                .toList();
    }

    public String getUserName() {
        return $x(userNameLabel).getText();
    }

    private static BookModel toModel(SelenideElement element) {
        ElementsCollection items = element.$$x(bookInTable);
        final String title = items.get(1).text();
        final String author = items.get(2).text();
        final String publisher = items.get(3).text();
        return new BookModel(title, author, publisher);
    }
}
