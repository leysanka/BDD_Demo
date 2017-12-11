package com.epam.bdd.ebay.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


@CucumberOptions( plugin = "json:target/cucumber-report.json",
                features = "src/test/resources/ebay",
                glue = "com.epam.bdd.ebay.steps", format = {"pretty"})
public class RunAddToCartTest extends AbstractTestNGCucumberTests {
   // private static WebDriver driver;

    @BeforeClass()
    public void beforeClass() {
       System.setProperty("webdriver", "chrome");
    }

   /* @AfterClass()
    public void tearDown(){
        driver.quit();
    }*/
}


