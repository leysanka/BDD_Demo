package com.epam.bdd.ebay.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;

public abstract class BasePage {

    protected WebDriver driver;
    private static Logger logger = LogManager.getLogger(BasePage.class.getSimpleName());
    private static final int SECONDS_TO_WAIT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

    public By getBy(Field field) {
        return new Annotations(field).buildBy();
    }

    public By getBy(String fieldName) {
        try {
            return new Annotations(this.getClass().getDeclaredField(fieldName)).buildBy();
        } catch (NoSuchFieldException e) {
            logger.error("Cannot get By locator for the specified element name.");
            return null;
        }
    }

    public void waitForElementIsClickable(WebElement element) {
        logger.info("Waiting for element is clickable.");
        WebDriverWait wait = new WebDriverWait(driver, SECONDS_TO_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForLoadingIsFinished() {
        logger.info("Waiting for loading is finished.");
        WebDriverWait wait = new WebDriverWait(driver, SECONDS_TO_WAIT);
        wait.until(ExpectedConditions.jsReturnsValue("return jQuery.active == 0;"));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}
