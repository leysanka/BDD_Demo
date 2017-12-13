package com.epam.bdd.ebay.steps;

import com.epam.bdd.ebay.driver.SingleDriver;
import com.epam.bdd.ebay.pages.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class AddToCartSteps {

    private static SingleDriver driver = new SingleDriver();

    @Given("^I stay in home page$")
    public HomePage navigateToHomePage() throws Throwable {
        HomePage homePage = new HomePage(driver);
        if (!driver.getCurrentUrl().equals(homePage.getHomePageUrl())) {
            driver.navigate().to(homePage.getHomePageUrl());
        }
        return homePage;
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

        Assert.assertTrue(resultsPage.getSearchResultsCountShownInPage() > 0, "Not found " + searchValue);
    }

    @Given("^The cart is empty$")
    public boolean theCartIsEmpty() throws Throwable {
        HomePage homePage = navigateToHomePage();
        if (homePage.isPresentCartCountLabel()) {
            CartViewPage cartPage =  homePage.goToCart();
            cartPage.deleteAllGoodsFromCart();
        }
        Assert.assertFalse(new CommonPage(driver).isPresentCartCountLabel(), "The cart is not empty.");
        return true;
    }


    @When("^I add to cart product number (\\d+) from the list of items shown in the Search Results$")
    public void addToCartProductNumberFromTheListOfItemsShownInTheSearchResults(int number) throws Throwable {
        (new SearchResultsPage(driver)).selectProductFromSearchList(number);
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
        if (theCartIsEmpty()) {
            productsAreFoundInBuyNow(product);
            addToCartProductNumberFromTheListOfItemsShownInTheSearchResults(1);
        }
    }
}
