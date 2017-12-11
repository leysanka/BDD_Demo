package com.epam.bdd.ebay.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;

    @FindBy(xpath = "//ul[@id='ListViewInner']/li")
    private List<WebElement> foundGoodsList;

    @FindBy(xpath = "//a[contains(@class, 'last_b')]")
    private WebElement buyNowButton;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getSearchResultsCountShownInPage() {
        return foundGoodsList.size();
    }

    public void showGoodsInBuyNow() {
        if (buyNowButton.isDisplayed()) {
            buyNowButton.click();
        }
    }
}
