package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final WebGetElementByLocatorAttribute getBehavior =
                WebGetElementByLocatorAttribute.getInstance(UiLocatorType.ID, locatorValue, attribute, attributeValue);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement instance");
    }
}
