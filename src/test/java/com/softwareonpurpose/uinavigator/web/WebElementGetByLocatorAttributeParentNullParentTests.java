package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeParentNullParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute_nullParent() {
        MockView.directNav();
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        String description = "Body";
        final String locatorValue = "body";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return WebElement instance");
    }
}
