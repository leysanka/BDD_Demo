package com.epam.bdd.ebay.pages;

import com.epam.bdd.ebay.exceptions.InvalidTestDataException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends CommonPage {

   // @FindBy(xpath = "//ul[@id='ListViewInner']/li")
    @FindBy(xpath = "//ul[@id='ListViewInner']/li/div[@iid]")
    private List<WebElement> foundGoodsList;

    @FindBy(xpath = "//a[contains(@class, 'last_b')]")
    private WebElement buyNowButton;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void selectFirstItemFromSearchList() {
        if (foundGoodsList.size() > 0) {
            foundGoodsList.get(0).click();
                waitForLoadingIsFinished();
            } else {
            throw new InvalidTestDataException("Search results are empty.");
        }
    }

    public int getSearchResultsCountShownInPage() {
        return foundGoodsList.size();
    }

    public void showGoodsInBuyNow() throws NoSuchFieldException {
        if (isElementPresent(getBy(this.getClass().getDeclaredField("buyNowButton")))) {
            waitForElementIsClickable(buyNowButton);
            buyNowButton.click();
        }
    }
}
