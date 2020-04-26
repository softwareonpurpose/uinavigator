package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final String locatorValue = "body";
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent);
        Class<WebGetElementByLocatorAttributeParent> expected = WebGetElementByLocatorAttributeParent.class;
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_idParentNull() {
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final String locatorValue = "name";
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(
                        UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent);
        Class<WebGetElementByLocatorAttributeParent> expected = WebGetElementByLocatorAttributeParent.class;
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_parentInstance() {
        final String attribute = "data-test";
        final String attributeValue = "initial";
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "form");
        final String locatorValue = "name";
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(
                        UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of WebElement";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_nullParent() {
        MockView.directNav();
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        final String locatorValue = "body";
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent);
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return WebElement instance");
    }
}
