package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class CookieViewerTests extends TestClass {
    @Test
    public void testGetInstance() {
        driver = (WebDriver) ChromeDriverInstantiation.getInstance().instantiateDriver();
        //noinspection rawtypes
        Class expected = CookieViewer.class;
        //noinspection rawtypes
        Class actual = CookieViewer.getInstance(driver).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of CookieViewer");
    }

    @Test
    public void testGetCookieValue_nameQualified() {
        driver = (WebDriver) ChromeDriverInstantiation.getInstance().instantiateDriver();
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
