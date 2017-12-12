package com.epam.bdd.ebay.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;




@CucumberOptions( plugin = "json:target/cucumber-report.json",
                features = "src/test/resources/ebay",
                glue = "com.epam.bdd.ebay.steps" ,
                format = {"pretty"} ,
                tags = "~@multiple" )

public class RunAddToCartTest extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setSystemPropertie(){
        System.setProperty("webdriver", "chrome");
    }
}


