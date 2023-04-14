package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Collections;
import java.util.List;

public class SortTests extends BaseTest{

    @Test
    public void sortItemsByPriceLowToHigh(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        List<Double> unsortedPrices = hp.getPrices();
        hp.sortByPriceLowToHigh();
        List<Double> sortedPrices = hp.getPrices();

        double lowestPrice = Collections.min(unsortedPrices),
                highestPrice = Collections.max(unsortedPrices);
        double firstItemPrice = sortedPrices.get(0),
                lastItemPrice = sortedPrices.get(sortedPrices.size() - 1);

        Assert.assertEquals(firstItemPrice, lowestPrice);
        Assert.assertEquals(lastItemPrice, highestPrice);
    }

    @Test
    public void sortItemsByPriceHighToLow(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        List<Double> unsortedPrices = hp.getPrices();
        hp.sortByPriceHighToLow();
        List<Double> sortedPrices = hp.getPrices();

        double lowestPrice = Collections.min(unsortedPrices),
                highestPrice = Collections.max(unsortedPrices);
        double firstItemPrice = sortedPrices.get(0),
                lastItemPrice = sortedPrices.get(sortedPrices.size() - 1);

        Assert.assertEquals(firstItemPrice, highestPrice);
        Assert.assertEquals(lastItemPrice, lowestPrice);
    }

    @Test
    public void sortItemsByNameAtoZ(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        List<String> unsortedItems = hp.getItemNames();
        hp.sortAlphabeticalAtoZ();
        List<String> sortedItems = hp.getItemNames();

        Assert.assertTrue(sortedItems.get(0).charAt(0) <= unsortedItems.get(0).charAt(0));
    }

    @Test
    public void sortItemsByNameZtoA(){
        LoginPage lp = new LoginPage(getDriver());
        lp.login();

        HomePage hp = new HomePage(getDriver());

        List<String> unsortedItems = hp.getItemNames();
        hp.sortAlphabeticalZtoA();
        List<String> sortedItems = hp.getItemNames();

        Assert.assertTrue(sortedItems.get(0).charAt(0) >= unsortedItems.get(0).charAt(0));
    }

}
