package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentBodyTagTests extends TestClass {
    @Test
    public void testExecute_bodyTag() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Body";
        final String parentLocatorValue = "body";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Body";
        final String locatorValue = "body";
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.TAG, locatorValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance when parent and element locators 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
