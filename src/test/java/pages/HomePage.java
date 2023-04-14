package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomePage extends BasePage {
    @FindBy(css = ".app_logo")
    public WebElement headerLogo;
    @FindBy(css = "#react-burger-menu-btn")
    public WebElement menuHamburgerButton;
    @FindBy(xpath = "//nav[@class='bm-item-list']/a")
    public List<WebElement> menuOptionElements;
    public By menuOptionsLocator = By.xpath("//nav[@class='bm-item-list']/a");
    @FindBy(css = ".inventory_item_price")
    public List<WebElement> priceElements;
    public By pricesLocator = By.cssSelector(".inventory_item_price");
    @FindBy(css = ".product_sort_container")
    public WebElement sortSelect;
    @FindBy(css = ".inventory_item_name")
    public List<WebElement> itemNameElements;
    public By itemNamesLocator = By.cssSelector(".inventory_item_name");
    @FindBy(css = ".social_twitter")
    public WebElement twitterLink;
    @FindBy(css = ".social_facebook")
    public WebElement facebookLink;
    @FindBy(css = ".social_linkedin")
    public WebElement linkedInLink;
    @FindBy(css = ".shopping_cart_link")
    public WebElement shoppingCartButton;
    @FindBy(css = "img.inventory_item_img")
    public List<WebElement> itemImageElements;
    public By itemImagesLocator = By.cssSelector("img.inventory_item_img");
    @FindBy(css = ".inventory_item")
    public List<WebElement> inventoryItemElements;
    public By inventoryItemsLocator = By.cssSelector(".inventory_item");
    @FindBy(css = "[class='btn btn_primary btn_small btn_inventory']")
    public List<WebElement> addToCartButtons;
    public By addToCartButtonsLocator = By.cssSelector("[class='btn btn_primary btn_small btn_inventory']");
    @FindBy(css = "[class='btn btn_secondary btn_small btn_inventory']")
    public List<WebElement> removeFromCartButtons;
    @FindBy(css = ".shopping_cart_badge")
    public WebElement cartItemQtBadge;
    public By itemPricesLocator = By.cssSelector(".inventory_list .inventory_item .inventory_item_price");


    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    public HomePage clickMenu(){
        menuHamburgerButton.click();
        return this;
    }

    public List<String> getMenuOptions(List<WebElement> menuOptions){
        waitForVisOfAllElements(menuOptionsLocator);

        List<String> options = new ArrayList<>();

        for(WebElement option : menuOptionElements){
            options.add(option.getText());
        }

        return options;
    }

    public List<Double> getPrices(){
        waitForVisOfAllElements(pricesLocator);

        List<Double> prices = new ArrayList<>();

        for(WebElement element : priceElements){
            prices.add(Double.parseDouble(element.getText().replaceAll("[$]", "")));
        }

        return prices;
    }

    public HomePage sortByPriceLowToHigh(){
        Select sort = new Select(sortSelect);
        sort.selectByValue("lohi");
        return this;
    }

    public HomePage sortByPriceHighToLow(){
        Select sort = new Select(sortSelect);
        sort.selectByValue("hilo");
        return this;
    }

    public HomePage sortAlphabeticalAtoZ(){
        Select sort = new Select(sortSelect);
        sort.selectByValue("az");
        return this;
    }

    public HomePage sortAlphabeticalZtoA(){
        Select sort = new Select(sortSelect);
        sort.selectByValue("za");
        return this;
    }

    public List<String> getItemNames(){
        waitForVisOfAllElements(itemNamesLocator);

        List<String> itemNames = new ArrayList<>();

        for(WebElement item : itemNameElements){
            itemNames.add(item.getText());
        }

        return itemNames;
    }

    public List<String> getImageSources(){
        waitForVisOfAllElements(itemImagesLocator);

        List<String> sources = new ArrayList<>();

        for(WebElement image : itemImageElements){
            sources.add(image.getAttribute("src"));
        }

        return sources;
    }

    public int getNumberOfItemsOnDisplay(){
        waitForVisOfAllElements(inventoryItemsLocator);
        List<WebElement> invItems = driver.findElements(inventoryItemsLocator);
        return invItems.size();
    }

    public Set<String> getUniqueImageSources(){
        List<String> imageSources = getImageSources();
        Set<String> uniqueImageSources = new HashSet<>();
        uniqueImageSources.addAll(imageSources);

        return uniqueImageSources;
    }

    public List<WebElement> getAllLinksOnPage(){
        waitForPresOfAllElements(By.tagName("a"));
        List<WebElement> links = driver.findElements(By.tagName("a"));
        return links;
    }

    public HomePage addItemToCart(){
        waitForPresOfAllElements(addToCartButtonsLocator);
        addToCartButtons.get(0).click();
        return this;
    }

    public HomePage addItemToCart(int itemIndex){
        waitForPresOfAllElements(addToCartButtonsLocator);
        addToCartButtons.get(0).click();
        return this;
    }

    public HomePage addItemToCart(List<WebElement> buttons, int itemIndex){
        waitForPresOfAllElements(addToCartButtonsLocator);
        buttons.get(itemIndex).click();
        return this;
    }

    public List<Double> getItemPrices(){
        waitForPresOfAllElements(itemPricesLocator);
        List<WebElement> priceElements = driver.findElements(itemPricesLocator);
        List<Double> prices = new ArrayList<>();

        for(WebElement price : priceElements){
            prices.add(Double.parseDouble(price.getText().replaceAll(".*\\$", "")));
        }

        return prices;
    }

    public double getItemPrice(int itemIndex){
        List<Double> prices = getItemPrices();
        return prices.get(itemIndex);
    }

    public HomePage clickCartButton(){
        shoppingCartButton.click();
        return this;
    }

}
