package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePage {

    protected WebDriver driver;

    public BasePage (WebDriver extDriver) { //constructor
        this.driver = extDriver;
    }
}
