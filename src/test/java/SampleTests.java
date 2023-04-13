import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SampleTests extends BaseTest{
    public String url = "https://www.saucedemo.com/";

    @Test
    public void navigateToSite(){
        driver.get(url);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void verifyLoginPageHeader(){
        driver.get(url);

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login_logo"))).isDisplayed());
    }

    @Test
    public void verifyLoginPageButton(){
        driver.get(url);

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))).isDisplayed());
    }

    @Test
    public void validLogin(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void verifyHomepageHeader(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".app_logo"))).isDisplayed());
    }

    @Test
    public void verifyMenuOptions(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#react-burger-menu-btn")));
        menuButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//nav[@class='bm-item-list']/a")));

        List<WebElement> menuOptions = driver.findElements(By.xpath("//nav[@class='bm-item-list']/a"));
        List<String> options = new ArrayList<>();

        for(WebElement option : menuOptions){
            options.add(option.getText());
        }

        Assert.assertEquals(menuOptions.size(), 4);

        Assert.assertTrue(options.contains("All Items"));
        Assert.assertTrue(options.contains("About"));
        Assert.assertTrue(options.contains("Logout"));
        Assert.assertTrue(options.contains("Reset App State"));
    }

    @Test
    public void sortItemsByPriceLowToHigh(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        List<WebElement> unsortedPricesEle = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> unsortedPrices = new ArrayList<>();
        for(WebElement price : unsortedPricesEle){
            unsortedPrices.add(Double.parseDouble(price.getText().replaceAll("[$]", "")));
        }

        WebElement sortSelectEle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product_sort_container")));
        Select sortSelect = new Select(sortSelectEle);
        sortSelect.selectByValue("lohi");

        List<WebElement> sortedPricesEle = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> sortedPrices = new ArrayList<>();
        for(WebElement price : sortedPricesEle){
            sortedPrices.add(Double.parseDouble(price.getText().replaceAll("[$]", "")));
        }

        Assert.assertEquals(sortedPrices.get(0), Collections.min(unsortedPrices));
        Assert.assertEquals(sortedPrices.get(sortedPrices.size()-1), Collections.max(unsortedPrices));
    }

    @Test
    public void sortItemsByNameAtoZ(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        List<WebElement> unsortedItemNamesEle = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> unsortedItemNames = new ArrayList<>();
        for(WebElement item : unsortedItemNamesEle){
            unsortedItemNames.add(item.getText());
        }

        WebElement sortSelectEle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product_sort_container")));
        Select sortSelect = new Select(sortSelectEle);
        sortSelect.selectByValue("az");

        List<WebElement> sortedNamesEle = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> sortedNames = new ArrayList<>();
        for(WebElement item : sortedNamesEle){
            sortedNames.add(item.getText());
        }

        Assert.assertTrue(sortedNames.get(0).charAt(0) <= unsortedItemNames.get(0).charAt(0));
    }

    @Test
    public void sortItemsByNameZtoA(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        List<WebElement> unsortedItemNamesEle = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> unsortedItemNames = new ArrayList<>();
        for(WebElement item : unsortedItemNamesEle){
            unsortedItemNames.add(item.getText());
        }

        WebElement sortSelectEle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product_sort_container")));
        Select sortSelect = new Select(sortSelectEle);
        sortSelect.selectByValue("za");

        List<WebElement> sortedNamesEle = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> sortedNames = new ArrayList<>();
        for(WebElement item : sortedNamesEle){
            sortedNames.add(item.getText());
        }

        Assert.assertTrue(sortedNames.get(0).charAt(0) >= unsortedItemNames.get(0).charAt(0));
    }

    @Test
    public void addItemToCart(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        WebElement backpackBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        backpackBtn.click();

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack"))).isDisplayed());
    }

    @Test
    public void verifyCartItemQtBadgeUpdates() {
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        WebElement backpackBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        WebElement bikelightBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bike-light")));
        backpackBtn.click();
        bikelightBtn.click();

        WebElement cartItemQtBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge")));

        Assert.assertEquals(Integer.parseInt(cartItemQtBadge.getText()), 2);
    }

    @Test
    public void verifyCartTotalUpdates(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        WebElement firstItemBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_list .inventory_item:nth-child(1) button")));
        WebElement secondItemBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_list .inventory_item:nth-child(2) button")));

        WebElement firstItemPriceEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_list .inventory_item:nth-child(1) .inventory_item_price")));
        WebElement secondItemPriceEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_list .inventory_item:nth-child(2) .inventory_item_price")));
        double firstItemPrice = Double.parseDouble(firstItemPriceEle.getText().replaceAll("[$]",""));
        double secondItemPrice = Double.parseDouble(secondItemPriceEle.getText().replaceAll("[$]",""));

        firstItemBtn.click();
        secondItemBtn.click();

        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartBtn.click();

        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutBtn.click();

        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        WebElement zipCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue")));
        firstNameField.sendKeys("dummy");
        lastNameField.sendKeys("foo");
        zipCodeField.sendKeys("90010");
        continueBtn.click();

        WebElement totalPriceLbl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_subtotal_label")));
        double totalPrice = Double.parseDouble(totalPriceLbl.getText().replaceAll(".*\\$", ""));  // alt regex: [Item total: $]

        Assert.assertEquals(firstItemPrice+secondItemPrice, totalPrice);
    }

    @Test
    public void validCheckout(){
        driver.get(url);

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        WebElement firstItemBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_list .inventory_item:nth-child(1) button")));
        WebElement secondItemBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_list .inventory_item:nth-child(2) button")));

        firstItemBtn.click();
        secondItemBtn.click();

        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartBtn.click();

        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutBtn.click();

        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        WebElement zipCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue")));
        firstNameField.sendKeys("dummy");
        lastNameField.sendKeys("foo");
        zipCodeField.sendKeys("90010");
        continueBtn.click();

        WebElement finishCheckoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishCheckoutBtn.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(), 'Checkout: Complete!')]"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header"))).isDisplayed());
    }

}
