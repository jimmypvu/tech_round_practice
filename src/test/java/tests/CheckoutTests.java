package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

public class CheckoutTests extends BaseTest{

    @Test
    public void validCheckout(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());
        List<WebElement> addItemButtons = hp.waitForAndGetAll(hp.addToCartButtonsLocator);
        hp.addItemToCart(addItemButtons, 0);
        hp.addItemToCart(addItemButtons, 1);
        hp.clickCartButton();

        CartPage cap = new CartPage(getDriver());
        cap.clickCheckoutButton();

        CheckoutPage cop = new CheckoutPage(getDriver());
        cop.fillOutAndClickContinue();
        cop.clickFinishCheckout();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(cop.checkoutSuccessHeader.isDisplayed());
        Assert.assertTrue(cop.checkoutSuccessMessage.isDisplayed());
    }
}
