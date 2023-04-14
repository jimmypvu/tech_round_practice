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

public class CartTests extends BaseTest{

    @Test
    public void addItemToCart(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());
        hp.addItemToCart();

        Assert.assertTrue(hp.removeFromCartButtons.size() >= 1);
    }

    @Test
    public void verifyCartItemQtBadgeUpdates() {
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());
        hp.addItemToCart(0);
        hp.addItemToCart(1);

        Assert.assertEquals(Integer.parseInt(hp.cartItemQtBadge.getText()), 2);
    }

    @Test
    public void verifyCartTotalUpdates(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());
        //need to get a static copy of all addToCartButtons bc @FindBy will reinitialize element list without
        //the first item the next time we call it (bc we changed the state of the first item by clicking it on the first call)
        List<WebElement> addToCartButtons = hp.waitForAndGetAll(hp.addToCartButtonsLocator);
        hp.addItemToCart(addToCartButtons, 0);
        hp.addItemToCart(addToCartButtons, 1);
        double firstItemPrice = hp.getItemPrice(0);
        double secondItemPrice = hp.getItemPrice(1);
        hp.clickCartButton();

        CartPage cap = new CartPage(getDriver());
        cap.clickCheckoutButton();

        CheckoutPage cop = new CheckoutPage(getDriver());
        cop.fillOutAndClickContinue();

        double itemSubtotal = cop.getSubtotal();

        Assert.assertEquals(firstItemPrice + secondItemPrice, itemSubtotal);
    }
}
