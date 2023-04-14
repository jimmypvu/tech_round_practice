package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions act;
    protected JavascriptExecutor js;

    public BasePage(WebDriver givenDriver){
        this.driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(500));
        act = new Actions(driver);
        js = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
    }

    public void waitFor(By locator){
        waitForPres(locator);
        waitForVis(locator);
//        waitForClk(locator);
    }

    public void waitForVisOfAllElements(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForPresOfAllElements(By locator){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForClk(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForClk(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForVis(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForPres(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForAndGet(By locator){
        waitFor(locator);
        return driver.findElement(locator);
    }

    public List<WebElement> waitForAndGetAll(By locator){
        waitForPresOfAllElements(locator);
        waitForVisOfAllElements(locator);
        return driver.findElements(locator);
    }

    public void pause(int millis) {
        try{
            Thread.sleep(millis);
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
