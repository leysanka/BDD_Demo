package com.epam.bdd.ebay.pages;

import com.epam.bdd.ebay.exceptions.WrongPageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductViewPage extends CommonPage {

    private static Logger logger = LogManager.getLogger(ProductViewPage.class.getSimpleName());
    private static final String PRODUCT_URL = "https://www.ebay.com/itm/";


    @FindBy(xpath = "//a[@id='isCartBtn_btn']")
    private WebElement addToCartButton;

    public ProductViewPage(WebDriver driver) {
        super(driver);
        if(!driver.getCurrentUrl().startsWith(PRODUCT_URL)) {
            throw new WrongPageException("Product view page url does not meet to the expected.");
        }
    }

    public void pressAddToCartButtonIfPresent() throws NoSuchFieldException {
        if (isElementPresent(getBy(this.getClass().getDeclaredField("addToCartButton")))) {
            if (addToCartButton.isEnabled()) {
                waitForElementIsClickable(addToCartButton);
                addToCartButton.click();
                waitForLoadingIsFinished();
            } else {
                logger.error("Add to cart button is disabled.");
            }
        } else {
            logger.error("No Add To Cart button is found on page.");
        }
    }


}
