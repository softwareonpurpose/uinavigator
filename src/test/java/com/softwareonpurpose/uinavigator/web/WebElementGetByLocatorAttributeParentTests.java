package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeParentTests extends TestClass {
    @Test
    public void testExecute_parentInstance() {
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final String locatorValue = "name";
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent, host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of WebElement";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Form";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        WebElementGet getParent = WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, "form", host);
        String description = "Name";
        final String locatorValue = "name";
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        getBehavior.execute();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of WebElement";
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
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "a";
        final String attributeValue = "home";
        final String attribute = "rel";
        final WebElementGetByLocatorAttributeParent getElement =
                WebElementGetByLocatorAttributeParent
                        .getInstance(
                                description, locatorType, locatorValue, attribute, attributeValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'iframe' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
