package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeOrdinalParentNullParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_parentNull() {
        String description = "View";
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final int ordinal = 1;
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        final String locatorValue = "body";
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementByLocatorAttributeOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue,
                                attribute, attributeValue, ordinal, getParent);
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }
}
