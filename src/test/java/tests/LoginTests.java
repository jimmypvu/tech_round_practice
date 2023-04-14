package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void verifyLoginPageURL(){
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void verifyLoginPageTitle(){
        Assert.assertEquals(BaseTest.getDriver().getTitle(), "Swag Labs");
    }

    @Test
    public void verifyLoginPageHeader(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());

        Assert.assertTrue(lp.loginPageHeader.isDisplayed());
    }

    @Test
    public void verifyLoginPageFields(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());

        Assert.assertTrue(lp.usernameField.isDisplayed());
        Assert.assertTrue(lp.passwordField.isDisplayed());
    }

    @Test
    public void verifyLoginPageButton(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());

        Assert.assertTrue(lp.loginButton.isDisplayed());
    }

    @Test
    public void validLogin(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());
        lp.login("standard_user", "secret_sauce");

        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void invalidLogin_LockedOutUser(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());
        lp.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(lp.loginErrorMessage.isDisplayed());
        Assert.assertTrue(lp.loginErrorMessage.getText().contains("user has been locked out"));
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url);
    }

    @Test
    public void invalidLogin_BadPassword(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());
        lp.login("standard_user", "bad_sauce");

        Assert.assertTrue(lp.loginErrorMessage.isDisplayed());
        Assert.assertTrue(lp.loginErrorMessage.getText().contains("Username and password do not match"));
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url);
    }

    @Test
    public void invalidLogin_EmptyPassword(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());
        lp.login("standard_user", "");

        Assert.assertTrue(lp.loginErrorMessage.isDisplayed());
        Assert.assertTrue(lp.loginErrorMessage.getText().contains("Password is required"));
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url);
    }

    @Test
    public void invalidLogin_EmptyUsername(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());
        lp.login("", "secret_sauce");

        Assert.assertTrue(lp.loginErrorMessage.isDisplayed());
        Assert.assertTrue(lp.loginErrorMessage.getText().contains("Username is required"));
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url);
    }

    @Test
    public void invalidLogin_EmptyLoginFields(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());
        lp.login("", "");

        Assert.assertTrue(lp.loginErrorMessage.isDisplayed());
        Assert.assertTrue(lp.loginErrorMessage.getText().contains("Username is required"));
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url);
    }

    @Test
    public void invalidLogin_NonexistentUser(){
        LoginPage lp = new LoginPage(BaseTest.getDriver());
        lp.login("idontexist", "secret_sauce");

        Assert.assertTrue(lp.loginErrorMessage.isDisplayed());
        Assert.assertTrue(lp.loginErrorMessage.getText().contains("Username and password do not match any user"));
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url);
    }
}
