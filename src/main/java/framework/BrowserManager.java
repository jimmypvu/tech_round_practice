package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserManager {

    public static WebDriver pickBrowser(String browser){
        switch(browser){
            case "firefox": return getFirefoxDriver();
            case "edge": return getEdgeDriver();
            default: return getChromeDriver();
        }
    }

    public static WebDriver getChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
//        co.addArguments("--headless");

        ChromeDriver driver = new ChromeDriver(co);
        manageDriver(driver);

        return driver;
    }

    public static WebDriver getFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();
        manageDriver(driver);

        return driver;
    }

    public static WebDriver getEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        EdgeDriver driver = new EdgeDriver();
        manageDriver(driver);

        return driver;
    }

    public static void manageDriver(WebDriver driver){
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

}
