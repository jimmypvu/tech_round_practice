package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;
import java.util.Set;

public class HomePageTests extends BaseTest{

    @Test
    public void verifyHomepageHeaderIsDisplayed(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        Assert.assertTrue(hp.headerLogo.isDisplayed());
    }

    @Test
    public void verifyMenuButtonIsDisplayed(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        Assert.assertTrue(hp.menuHamburgerButton.isDisplayed());
    }

    @Test
    public void verifyMenuOptionsAreDisplayed(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());
        hp.clickMenu();

        List<String> menuOptions = hp.getMenuOptions(hp.menuOptionElements);

        Assert.assertEquals(hp.menuOptionElements.size(), 4);
        Assert.assertTrue(menuOptions.contains("All Items"));
        Assert.assertTrue(menuOptions.contains("About"));
        Assert.assertTrue(menuOptions.contains("Logout"));
        Assert.assertTrue(menuOptions.contains("Reset App State"));
    }

    @Test
    public void verifySocialLinksAreDisplayed(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        Assert.assertTrue(hp.twitterLink.isDisplayed());
        Assert.assertTrue(hp.facebookLink.isDisplayed());
        Assert.assertTrue(hp.linkedInLink.isDisplayed());
    }

    @Test
    public void verifyCartIconIsDisplayed(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        Assert.assertTrue(hp.shoppingCartButton.isDisplayed());
    }

    @Test
    public void verifyItemImagesAreDisplayed(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();
//        if logged in as the problem_user, images don't render correctly (same image is rendered for every product)
//        lp.login("problem_user", "secret_sauce");

        HomePage hp = new HomePage(getDriver());
        int numOfItemsDisplayed = hp.getNumberOfItemsOnDisplay();

        Set<String> uniqueImageSources = hp.getUniqueImageSources();
        int numOfUniqueItemImagesDisplayed = uniqueImageSources.size();

        Assert.assertTrue(numOfUniqueItemImagesDisplayed >= numOfItemsDisplayed);
    }

}
