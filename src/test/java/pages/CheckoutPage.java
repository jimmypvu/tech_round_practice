package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{
    @FindBy(id = "first-name")
    public WebElement firstNameField;
    @FindBy(id = "last-name")
    public WebElement lastNameField;
    @FindBy(id = "postal-code")
    public WebElement zipCodeField;
    @FindBy(id = "continue")
    public WebElement continueButton;
    @FindBy(id = "finish")
    public WebElement finishCheckoutButton;
    @FindBy(css = ".summary_subtotal_label")
    public WebElement subtotalLabel;
    public By subtotalLabelLocator = By.cssSelector(".summary_subtotal_label");
    @FindBy(xpath = "//span[@class='title'][contains(text(), 'Checkout: Complete!')]")
    public WebElement checkoutSuccessHeader;
    @FindBy(css = ".complete-header")
    public WebElement checkoutSuccessMessage;

    public CheckoutPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public CheckoutPage enterFirstName(String firstName){
        firstNameField.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        lastNameField.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterZipCode(String zipCode){
        zipCodeField.sendKeys(zipCode);
        return this;
    }

    public CheckoutPage fillOutAndClickContinue(String fn, String ln, String zip){
        enterFirstName(fn);
        enterLastName(ln);
        enterZipCode(zip);
        continueButton.click();
        return this;
    }

    public CheckoutPage fillOutAndClickContinue(){
        enterFirstName("dummy");
        enterLastName("foo");
        enterZipCode("90010");
        continueButton.click();
        return this;
    }

    public CheckoutPage clickFinishCheckout(){
        finishCheckoutButton.click();
        return this;
    }

    public double getSubtotal(){
        waitFor(subtotalLabelLocator);
        return Double.parseDouble(subtotalLabel.getText().replaceAll(".*\\$", ""));
    }

}
