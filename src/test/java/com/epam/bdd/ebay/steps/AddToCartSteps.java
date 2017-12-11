package com.epam.bdd.ebay.steps;

import com.epam.bdd.ebay.driver.SingleDriver;
import com.epam.bdd.ebay.pages.HomePage;
import com.epam.bdd.ebay.pages.SearchResultsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class AddToCartSteps {

    private static SingleDriver driver = new SingleDriver();

    private int foundResultsSize;

    @Given("^I stay in home page$")
    public void navigateToHomePage() throws Throwable {
        HomePage homePage = new HomePage(driver);
        if (!driver.getCurrentUrl().equals(homePage.getHomePageUrl())) {
            driver.navigate().to(homePage.getHomePageUrl());
        }
    }

    @When("^I search for \"([^\"]*)\"$")
    public void performSearchInHomePage(String searchValue) throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.setSearchFieldValue(searchValue);
        homePage.pressSearchButton();
    }

    @Then("^Search results are found$")
    public void searchResultsAreFound() throws Throwable {
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        Assert.assertTrue(resultsPage.getSearchResultsCountShownInPage() > 0, "Not found." );
    }

    @Given("^\"([^\"]*)\" products are found in BuyNow$")
    public void productsAreFoundInBuyNow(String searchValue) throws Throwable {
        navigateToHomePage();
        performSearchInHomePage(searchValue);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        resultsPage.showGoodsInBuyNow();
        foundResultsSize = resultsPage.getSearchResultsCountShownInPage();
        Assert.assertTrue(foundResultsSize > 0, "Not found " + searchValue);

    }

    @And("^The cart is empty$")
    public void theCartIsEmpty() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
