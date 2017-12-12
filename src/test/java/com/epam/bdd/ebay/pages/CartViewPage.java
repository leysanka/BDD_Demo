package com.epam.bdd.ebay.pages;

import com.epam.bdd.ebay.exceptions.WrongPageException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartViewPage extends CommonPage {

    private static final String CART_URL = "https://cart.payments.ebay.com";

    @FindBy(xpath = "//div[@id='ShopCart']//div[contains(@id, 'sellerBucket')]")
    private List<WebElement> allGoodsInShopCart;

    @FindBy(xpath = "//div[@id='ShopCart']//a[contains(@class, 'actionLink')][1]")
    private List<WebElement> deleteButtonsForShopCartGoods;

    public CartViewPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().startsWith(CART_URL)) {
            throw new WrongPageException("Cart view url does not meet to the expected.");
        }
    }

    public void deleteAllGoodsFromCart() {
        if (allGoodsInShopCart.size() > 0 ){
            do {
                WebElement deleteButton = deleteButtonsForShopCartGoods.get(0);
                scrollToElement(deleteButton);
                waitForElementIsClickable(deleteButton);
                deleteButton.click();
            }
            while (allGoodsInShopCart.size() > 0);
        }

       /* if (allGoodsInShopCart.size() > 0 ) {
            for (int i = 0; i < allGoodsInShopCart.size(); i++){
                WebElement deleteButton = deleteButtonsForShopCartGoods.get(i);
                scrollToElement(deleteButton);
                waitForElementIsClickable(deleteButton);
                deleteButton.click();
            }
        }*/
    }
}
