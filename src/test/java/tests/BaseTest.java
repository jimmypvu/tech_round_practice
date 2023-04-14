package tests;

import framework.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public static String url = "https://www.saucedemo.com/";
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void setupAndLaunchBrowser(){
        threadLocal.set(BrowserManager.pickBrowser("chrome"));

        getDriver().get(url);
    }

    public static WebDriver getDriver(){
        return threadLocal.get();
    }

    @AfterMethod
    public void teardown(){
        getDriver().quit();
        threadLocal.remove();
    }

}
