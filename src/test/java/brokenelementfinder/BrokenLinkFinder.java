package brokenelementfinder;

import org.openqa.selenium.WebElement;
import tests.BaseTest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinkFinder {

    public static void verifyBrokenLinks(List<WebElement> links) {
        HttpURLConnection connection = null;

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) {
                System.out.println(url + " - URL is empty or not configured");
            } else if (!url.startsWith(BaseTest.url)) {
                System.out.println(url + " - URL belongs to another domain");
            } else {
                try {
                    connection = (HttpURLConnection) (new URL(url)).openConnection();
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    System.out.println(url + " - " + responseCode);

                    if (responseCode >= 400) {
                        System.out.println(url + " is a broken link");
                    } else {
                        System.out.println(url + " is a valid link");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
