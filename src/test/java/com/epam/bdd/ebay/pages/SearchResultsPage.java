package com.epam.bdd.ebay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage {

    @FindBy(xpath = "//ul[@id='ListViewInner']/li")
    private List<WebElement> foundGoodsList;


}
