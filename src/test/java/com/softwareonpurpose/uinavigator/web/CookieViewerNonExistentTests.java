package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class CookieViewerNonExistentTests extends TestClass {
    @Test
    public void testGetCookieValue_nameQualifiedNonExistent() {
        driver = (WebDriver) ChromeDriverInstantiation.getInstance().instantiateDriver();
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
}
