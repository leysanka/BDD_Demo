package com.epam.bdd.ebay.steps;

import com.epam.bdd.ebay.driver.SingleDriver;
import com.epam.bdd.ebay.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class AddToCartSteps {

    private static SingleDriver driver = new SingleDriver();


    @Given("^I stay in home page$")
    public void iStayInHomePage() throws Throwable {
        navigateToHomePage();
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
        Assert.assertTrue(resultsPage.getSearchResultsCountShownInPage() > 0, "Nothing found.");
    }

    @Given("^\"([^\"]*)\" products are present in 'Buy now' search results$")
    public void productsAreFoundInBuyNow(String searchValue) throws Throwable {
        navigateToHomePage();
        performSearchInHomePage(searchValue);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        resultsPage.showGoodsInBuyNow();

        Assert.assertTrue(resultsPage.getSearchResultsCountShownInPage() > 0, "Not found " + searchValue);
    }

    @Given("^The cart is empty$")
    public void theCartIsEmpty() throws Throwable {
        cleanCartIfNotEmpty();
        Assert.assertFalse(new CommonPage(driver).isPresentCartCountLabel(), "The cart is not empty.");
    }

    private void cleanCartIfNotEmpty() {
        HomePage homePage = navigateToHomePage();
        if (homePage.isPresentCartCountLabel()) {
            CartViewPage cartPage =  homePage.goToCart();
            cartPage.deleteAllGoodsFromCart();
        }
    }

    @When("^I add to cart first found product from the search results list$")
    public void addToCartFirtsProductFromTheTheSearchResultsList() throws Throwable {
        (new SearchResultsPage(driver)).selectFirstItemFromSearchList();
        (new ProductViewPage(driver)).pressAddToCartButtonIfPresent();
    }

    @Then("^I see the cart view page is opened$")
    public void seeTheCartViewPageIsOpened() throws Throwable {
       Assert.assertNotNull(new CartViewPage(driver), "The cart view is not opened.");
    }

    @And("^Selected product is shown there$")
    public void selectedProductIsShownThere() throws Throwable {

    }

    @And("^The cart label shows (\\d+) count of products in it$")
    public void theCartLabelShowsCountOfProductsInIt(int count) throws Throwable {
       Assert.assertEquals(count, new CommonPage(driver).getCartCountValueIfPresent(), "Count of products added to cart does not meet to the ");
    }

    @Given ("^The \"([^\"]*)\" product has already been added to cart$")
    public void theProductHasBeenAddedToCart(String product) throws Throwable {
        cleanCartIfNotEmpty();
            productsAreFoundInBuyNow(product);
        addToCartFirtsProductFromTheTheSearchResultsList();
    }

    //@After("@base")
    @After
    public void cleanCartAfterMultipleFeatureRun() {
        cleanCartIfNotEmpty();
    }

    private HomePage navigateToHomePage() {
        HomePage homePage = new HomePage(driver);
        if (!driver.getCurrentUrl().equals(homePage.getHomePageUrl())) {
            driver.navigate().to(homePage.getHomePageUrl());
        }
        return homePage;
    }
}
