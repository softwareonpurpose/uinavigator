package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class CookieViewerFullyQualifiedMatchTests extends TestClass {
    @Test
    public void testGetCookieValue_fullyQualifiedMatch() {
        driver = ChromeUiDriverService.getInstance().getDriver();
        String uri = "http://www.google.com";
        driver.navigate().to(uri);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.cookie = \"cookiename=cookievalue\";");
        String expected = "cookievalue";
        CookieViewer viewer = CookieViewer.getInstance(driver);
        String actual = viewer.getCookieValue("cookiename", "www.google.com", "/");
        Assert.assertEquals(actual, expected, "Failed to return expected cookie value");
    }
}
