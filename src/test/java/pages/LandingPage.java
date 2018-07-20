package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

public class LandingPage extends BasePage {

    private  static String landingURL = "https://www.autohero.com/de/search/";
    private static String erstzDDxpath = "//*[text()='Erstzulassung ab']";
    private static String yearDD = "//*[@name='yearRange.min']";
    private static String activeFilter = "//*[@data-qa-selector='active-filter']";
    private static String sort = "//*[@name='sort']";
    private static String yearsList = "//*[@data-qa-selector='results-found']/div/div//li[@data-qa-selector='spec'][1]";
    private static String priceList = "//*[@data-qa-selector='results-found']/div/div//div[@data-qa-selector='price']";

    public LandingPage(WebDriver extDriver) {
        super(extDriver);
        driver.get(landingURL);
    }

    public LandingPage expandErstzDD() {
        driver.findElement(By.xpath(erstzDDxpath)).click();
        return this;
    }

    public LandingPage selectYear(String year) {
       new Select(driver.findElement(By.xpath(yearDD))).selectByVisibleText(year);
       new WebDriverWait(driver, 5, 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(activeFilter)));
        return this;
    }

    public LandingPage selectSort(String sortValue) {
        new Select(driver.findElement(By.xpath(sort))).selectByVisibleText(sortValue);
        return this;
    }

    public void verifyYear(String year) {
        ArrayList<WebElement> years = (ArrayList<WebElement>) driver.findElements(By.xpath(yearsList));
        for (WebElement element: years) {
            Assert.assertTrue(Integer.parseInt(element.getText().substring(2)) >= Integer.parseInt(year));
        }
    }

    public void verifyPrice() {
        ArrayList<WebElement> prices = (ArrayList<WebElement>) driver.findElements(By.xpath(priceList));

        // make a copy of the list
        ArrayList<Float> sortedPrices = new ArrayList<Float>();

        for (WebElement element: prices) {
            sortedPrices.add(Float.parseFloat(element.getText().substring(0,6)));
        }

        ArrayList<Float> cutPrices = new ArrayList<Float>(sortedPrices);

        // sort the list
        Collections.sort(sortedPrices);
        Collections.reverse(sortedPrices);

        // true if the prices are sorted
        Assert.assertTrue(sortedPrices.equals(cutPrices));
    }
}
