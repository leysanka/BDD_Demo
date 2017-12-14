package com.epam.bdd.ebay.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@CucumberOptions(strict = true,
        plugin = {"com.github.kirlionik.cucumberallure.AllureReporter",
                "json:target/cucumber-report.json"},
        features = "src/test/resources/",
        glue = "com.epam.bdd.ebay",
        format = {"pretty"},
        tags = "~@multiple")

public class RunEbayCucumberTests extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setSystemProperties() {
        System.setProperty("webdriver", "chrome");
    }
}


