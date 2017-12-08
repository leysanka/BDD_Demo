package com.epam.bdd.ebay.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//input[@id='gh-ac']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@id='gh-btn']")
    private WebElement searchButton;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



}
