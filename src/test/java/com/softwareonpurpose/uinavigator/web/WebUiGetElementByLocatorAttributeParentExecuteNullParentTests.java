package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiGetElementByLocatorAttributeParentExecuteNullParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nullParent() {
        String description = "Body";
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final String locatorValue = "body";
        final WebUiGetElement getParent = null;
        //noinspection ConstantConditions
        final WebUiGetElementByLocatorAttributeParent getBehavior =
                WebUiGetElementByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return WebElement instance");
    }
}
