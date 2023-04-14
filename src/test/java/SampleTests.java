import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class SampleTests extends BaseTest {


//

//
//    @Test
//    public void validCheckout(){
//        driver.get(url);
//
//        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
//        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
//
//        loginField.sendKeys("standard_user");
//        passwordField.sendKeys("secret_sauce", Keys.ENTER);
//
//        WebElement firstItemBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_list .inventory_item:nth-child(1) button")));
//        WebElement secondItemBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_list .inventory_item:nth-child(2) button")));
//
//        firstItemBtn.click();
//        secondItemBtn.click();
//
//        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
//        cartBtn.click();
//
//        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
//        checkoutBtn.click();
//
//        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
//        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
//        WebElement zipCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
//        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue")));
//        firstNameField.sendKeys("dummy");
//        lastNameField.sendKeys("foo");
//        zipCodeField.sendKeys("90010");
//        continueBtn.click();
//
//        WebElement finishCheckoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
//        finishCheckoutBtn.click();
//
//        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
//        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(), 'Checkout: Complete!')]"))).isDisplayed());
//        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header"))).isDisplayed());
//    }

}
