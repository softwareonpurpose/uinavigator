package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        Class expected = RemoteWebElement.class;
        MockView.directNav();
        Class actual =
                WebGetElementByLocatorOrdinal.getInstance(new By.ByTagName("option"), 5).execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement");
    }

    @Test
    public void testExecute_ordinalGreaterThanList() {
        MockView.directNav();
        final By.ByTagName locator = new By.ByTagName("label");
        final WebElement actual = WebGetElementByLocatorOrdinal.getInstance(locator, 5).execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is greater than list size");
    }
}
