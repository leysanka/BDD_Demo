package com.epam.bdd.ebay.pages;

import com.epam.bdd.ebay.exceptions.ElementNotPresentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonPage extends BasePage {

    @FindBy(xpath = "//i[@id='gh-cart-n']")
    private WebElement cartCountLabel;

    @FindBy(xpath = "//i[@id='gh-cart-i']")
    private WebElement cartIcon;

    private static Logger logger = LogManager.getLogger(CommonPage.class.getSimpleName());

    public CommonPage(WebDriver driver) {
      super(driver);
    }

    public boolean isPresentCartCountLabel() {
        try {
            return isElementPresent(getBy(CommonPage.class.getDeclaredField("cartCountLabel")));
        } catch (NoSuchFieldException e) {
            logger.error("");
            throw new ElementNotPresentException("");
        }
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
