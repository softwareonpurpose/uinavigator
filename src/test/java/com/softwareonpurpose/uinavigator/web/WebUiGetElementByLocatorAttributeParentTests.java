package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiGetElementByLocatorAttributeParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        String description = "View";
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final String locatorValue = "body";
        final WebUiGetElement getParent = null;
        //noinspection ConstantConditions
        final WebUiGetElementByLocatorAttributeParent getBehavior =
                WebUiGetElementByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent);
        Class<WebUiGetElementByLocatorAttributeParent> expected = WebUiGetElementByLocatorAttributeParent.class;
        //noinspection rawtypes
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_idParentNull() {
        String description = "View";
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final String locatorValue = "name";
        final WebUiGetElement getParent = null;
        //noinspection ConstantConditions
        final WebUiGetElementByLocatorAttributeParent getBehavior =
                WebUiGetElementByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent);
        Class<WebUiGetElementByLocatorAttributeParent> expected = WebUiGetElementByLocatorAttributeParent.class;
        //noinspection rawtypes
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_parentInstance() {
        String parentDescription = "Form";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        WebUiGetElement getParent = WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, "form");
        String description = "Name";
        final String locatorValue = "name";
        final WebUiGetElementByLocatorAttributeParent getBehavior =
                WebUiGetElementByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of WebElement";
        Assert.assertEquals(actual, expected, message);
    }
}
