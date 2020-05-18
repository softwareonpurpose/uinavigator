package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Username";
        final String locatorValue = "user_name";
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Username";
        final String locatorValue = "user_name";
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        getElement.execute();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

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
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent
                        .getInstance(description, UiLocatorType.CLASS, locatorValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'iframe' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
