package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentTests extends TestClass {
    @Test
    public void testConstructor_bodyTag() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Username";
        final String parentLocatorValue = "user_name";
        final WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.NAME, parentLocatorValue, host);
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, host);
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual;
        actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Username";
        final String parentLocatorValue = "user_name";
        final WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.NAME, parentLocatorValue, host);
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, host);
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
        getElement.execute();
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_parentIFrame() {
        UiHost host = UiHost.getInstance();
        MockViewFramed.directNav(host);
        String parentDescription = "IFrame";
        final String parentLocatorValue = "iframe";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Title";
        final String locatorValue = "site-title";
        int ordinal = 1;
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.CLASS, locatorValue, ordinal, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'iframe' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
