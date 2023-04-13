import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions act;
    public JavascriptExecutor js;
    public String url = "https://www.google.com/";
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(500));

        act = new Actions(driver);

        js = (JavascriptExecutor) driver;

        driver.get(url);
    }

    public static WebDriver getDriver(){
        return threadLocal.get();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
