package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        this.driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown () {
        this.driver.quit();
    }
}
