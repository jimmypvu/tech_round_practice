package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = ".login_logo")
    public WebElement loginPageHeader;
    @FindBy(id = "login-button")
    public WebElement loginButton;
    @FindBy(css = "#user-name")
    public WebElement usernameField;
    @FindBy(css = "#password")
    public WebElement passwordField;
    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement loginErrorMessage;


    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public LoginPage login(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password, Keys.ENTER);
        return this;
    }

    public LoginPage login(){
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);
        return this;
    }


}
