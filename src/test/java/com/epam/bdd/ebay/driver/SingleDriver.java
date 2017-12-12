package com.epam.bdd.ebay.driver;

import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.concurrent.TimeUnit;

public class SingleDriver extends EventFiringWebDriver {

    private static final WebDriver DRIVER = WebDriverFactory.createDefault();
    private static final int IMPL_WAIT_TIMEOUT_SEC = 10;


    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
           DRIVER.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SingleDriver() {
        super(DRIVER);
        manage().timeouts().implicitlyWait(IMPL_WAIT_TIMEOUT_SEC, TimeUnit.SECONDS);
        manage().deleteAllCookies();
        manage().window().maximize();
    }

   /* Does not go there by some reason although written in cucumber sample as working approach, thus moved to constructor
    @Before
    public void setupDriver(){
        manage().timeouts().implicitlyWait(IMPL_WAIT_TIMEOUT_SEC, TimeUnit.SECONDS);
        manage().deleteAllCookies();
        manage().window().maximize();
    }*/

}
