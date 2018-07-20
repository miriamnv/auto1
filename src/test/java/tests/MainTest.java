package tests;

import org.testng.annotations.Test;
import pages.LandingPage;

public class MainTest extends BaseTest {

    @Test
    public void filterTest() {
        LandingPage landingPage = new LandingPage(driver);

        landingPage.expandErstzDD().selectYear("2015");

        landingPage.selectSort("Höchster Preis");

        landingPage.verifyYear("2015");

        landingPage.verifyPrice();

    }
}
