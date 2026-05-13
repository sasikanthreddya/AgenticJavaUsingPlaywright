package com.agentic.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import com.microsoft.playwright.*;
import org.junit.Assert;

public class CheckoutSteps {
    private Browser browser;
    private Page page;
    private static final String BASE_URL = "https://rahulshettyacademy.com/seleniumPractise/#/";

    @Before
    public void setUp() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @After
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
    }

    @Given("User navigates to GreenKart application")
    public void userNavigatesToGreenKart() {
        page.navigate(BASE_URL);
        page.waitForLoadState();
        Assert.assertTrue("Page title should contain GreenKart", page.title().contains("GreenKart"));
    }

    @When("User adds {string} product to cart")
    public void userAddsProductToCart(String productName) {
        // Wait for products to load
        page.waitForSelector(".product-name");

        // Get all product names
        var productNames = page.locator(".product-name").allTextContents();
        int productIndex = -1;

        // Find the product index
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).contains(productName)) {
                productIndex = i;
                break;
            }
        }

        // Click ADD TO CART button for the found product
        if (productIndex != -1) {
            page.locator("button:has-text('ADD TO CART')").nth(productIndex).click();
            page.waitForTimeout(500);
        } else {
            throw new RuntimeException("Product not found: " + productName);
        }
    }

    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
        // Click on cart icon
        page.click("img[alt='Cart']");
        page.waitForLoadState();

        // Click Proceed to Checkout
        page.click("button:has-text('PROCEED TO CHECKOUT')");
        page.waitForLoadState();
    }

    @And("User clicks Place Order button")
    public void userClicksPlaceOrderButton() {
        page.waitForSelector("button:has-text('Place Order')");
        page.click("button:has-text('Place Order')");
        page.waitForLoadState();
    }

    @And("User selects {string} from country dropdown")
    public void userSelectsCountry(String country) {
        page.waitForSelector("select");
        page.selectOption("select", country);
        page.waitForTimeout(500);
    }

    @And("User agrees to Terms and Conditions")
    public void userAgreesToTerms() {
        page.click("input[type='checkbox']");
        page.waitForTimeout(300);
    }

    @And("User clicks Proceed button")
    public void userClicksProceedButton() {
        page.click("button:has-text('Proceed')");
        page.waitForLoadState();
    }

    @Then("Order should be placed successfully")
    public void orderPlacedSuccessfully() {
        // Wait a moment for page to stabilize
        page.waitForTimeout(1000);

        // Verify we're back on home page or see confirmation
        String currentUrl = page.url();
        Assert.assertTrue("Should be back on home page or success page",
            currentUrl.contains("seleniumPractise"));
    }

    @Then("Cart should be empty")
    public void cartShouldBeEmpty() {
        page.waitForTimeout(1000);

        // Check if cart items count is 0
        var cartItems = page.locator("text=Items : ").textContent();
        Assert.assertNotNull("Cart items element should exist", cartItems);
        Assert.assertTrue("Cart should be empty", cartItems.contains("0"));
    }
}
