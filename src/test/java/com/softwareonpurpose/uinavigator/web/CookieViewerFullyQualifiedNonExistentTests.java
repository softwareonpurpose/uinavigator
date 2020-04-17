package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class CookieViewerFullyQualifiedNonExistentTests {
    private WebDriver driver;

    @AfterMethod
    public void terminate() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGetCookieValue_fullyQualifiedDomainOnly() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = null;
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("nonexistent", "www.google.com", "/nonexistent");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_fullyQualifiedPathOnly() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = null;
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("nonexistent", "nonexistent", "/");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_fullyQualifiedDomainPath() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = null;
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue(null, "www.google.com", "/");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

}
