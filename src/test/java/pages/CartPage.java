package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    public CartPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public CartPage clickCheckoutButton(){
        checkoutButton.click();
        return this;
    }


}
