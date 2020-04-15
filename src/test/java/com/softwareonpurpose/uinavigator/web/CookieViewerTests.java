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
        Class expected = CookieViewer.class;
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        Class actual = CookieViewer.getInstance(driver).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of CookieViewer");
    }

    @Test
    public void testGetCookieValue_nonexistent() {
        String expected = null;
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("unknown", "domain", "path");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return an instance of CookieViewer");
    }

    @Test
    public void testGetCookieValue_nameQualified() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename");
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_nameQualifiedNonExistent() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = null;
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("nonexistent");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_nameDomainQualifiedMatch() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename", "www.google.com");
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_nameDomainQualifiedDomainOnly() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = null;
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("nonexistent", "www.google.com");
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_nameDomainQualifiedNameOnly() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename", "nonexistent");
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_fullyQualifiedMatch() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename", "www.google.com", "/");
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_fullyQualifiedNameOnly() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename", "nonexistent", "/nonexistent");
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
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
    public void testGetCookieValue_fullyQualifiedNameDomain() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename", "www.google.com", null);
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }

    @Test
    public void testGetCookieValue_fullyQualifiedNamePath() {
        driver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename", null, "/");
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
