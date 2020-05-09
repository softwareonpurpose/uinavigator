package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class CookieViewerTests {
    private WebDriver driver;

    @AfterMethod
    public void terminate() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGetInstance() {
        driver = ChromeDriver.getInstance().instantiateDriver();
        Class expected = CookieViewer.class;
        Class actual = CookieViewer.getInstance(driver).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of CookieViewer");
    }

    @Test
    public void testGetCookieValue_nameQualified() {
        driver = ChromeDriver.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename");
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }
}
