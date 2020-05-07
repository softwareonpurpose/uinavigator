package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorOrdinalParentExecuteOneOfListTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String description = "Select";
        final String parentLocatorValue = "select";
        WebGetElementBehavior getParent =
                WebGetElementByLocator.getInstance(description, UiLocatorType.TAG, parentLocatorValue);
        final String locatorValue = "option";
        final int ordinal = 4;
        final WebGetElementByLocatorOrdinalParent getBehavior =
                WebGetElementByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of RemoteWebElement";
        Assert.assertEquals(actual, expected, message);
    }
}
