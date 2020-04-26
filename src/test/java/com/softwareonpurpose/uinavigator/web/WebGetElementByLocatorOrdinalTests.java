package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
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
        final String locatorValue = "option";
        final int ordinal = 5;
        final WebGetElementBehavior getElement =
                WebGetElementByLocatorOrdinal.getInstance(UiLocatorType.TAG, locatorValue, ordinal);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        Class actual = getElement.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement");
    }
}
