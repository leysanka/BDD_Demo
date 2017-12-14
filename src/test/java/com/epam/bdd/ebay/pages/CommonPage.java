package com.epam.bdd.ebay.pages;

import com.epam.bdd.ebay.exceptions.ElementNotPresentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage extends BasePage {

    private static final By CART_COUNT_LABEL_XPATH = By.xpath("//i[@id='gh-cart-n']");

    @FindBy(xpath = "//i[@id='gh-cart-n']")
    private WebElement cartCountLabel;

    @FindBy(xpath = "//i[@id='gh-cart-i']")
    private WebElement cartIcon;

    private static Logger logger = LogManager.getLogger(CommonPage.class.getSimpleName());

    public CommonPage(WebDriver driver) {
      super(driver);
    }


    public boolean isPresentCartCountLabel() {
        return isElementPresent(CART_COUNT_LABEL_XPATH);
    }

    public int getCartCountValueIfPresent() {
        if (isPresentCartCountLabel()) {
            return Integer.parseInt(cartCountLabel.getText());
        }
        throw new ElementNotPresentException("Cart count label is not present.");
    }

    public CartViewPage goToCart() {
        scrollToElement(cartIcon);
        cartIcon.click();
        waitForLoadingIsFinished();
        return new CartViewPage(driver);
    }

    public WebElement getCartCountLabel() {
        return cartCountLabel;
    }
}
