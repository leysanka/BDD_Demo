package com.epam.bdd.ebay.driver;

import com.epam.bdd.ebay.exceptions.UnknownDriverTypeException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.String.*;

final class WebDriverFactory {

    private WebDriverFactory() {
    }

    static WebDriver createDefault() {

        String webDriverProperty = System.getProperty("webdriver");

        if (webDriverProperty == null || webDriverProperty.isEmpty()) {
            throw new IllegalStateException("The webdriver system property must be set");
        }
        return Drivers.getDriverOfType(webDriverProperty).newDriver();
    }

        private enum Drivers {
            FIREFOX {
                @Override
                public WebDriver newDriver() {
                   return new FirefoxDriver();
                }
            }, CHROME {
                @Override
                public WebDriver newDriver() {
                     return new ChromeDriver();
                }
            };

            public static Drivers getDriverOfType(String type){
                for (Drivers driver :
                        Drivers.values()) {
                    if (type.equalsIgnoreCase(driver.toString())) {
                        return driver;
                    }
                }
                String msg = format("The 'webdriver' property type %s is not supported driver type. Valid types are %s",
                                        type, Drivers.values().toString());
                throw new UnknownDriverTypeException(msg);
            }

            public abstract WebDriver newDriver();

        }
}





