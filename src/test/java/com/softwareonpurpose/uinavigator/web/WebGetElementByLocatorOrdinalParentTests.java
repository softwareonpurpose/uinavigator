package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorOrdinalParentTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorOrdinalParent getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebGetElementByLocatorOrdinalParent> expected = WebGetElementByLocatorOrdinalParent.class;
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTag() {
        final String parentLocatorValue = "user_name";
        final WebGetElementByLocator getParent =
                WebGetElementByLocator.getInstance(UiLocatorType.NAME, parentLocatorValue);
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebGetElementByLocatorOrdinalParent getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebGetElementByLocatorOrdinalParent> expected = WebGetElementByLocatorOrdinalParent.class;
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_labelTagParentNull() {
        final String locatorValue = "label";
        final int ordinal = 2;
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorOrdinalParent getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.NAME, locatorValue, ordinal, getParent);
        Class<WebGetElementByLocatorOrdinalParent> expected = WebGetElementByLocatorOrdinalParent.class;
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_bodyTagParentNull() {
        final String locatorValue = "body";
        final int ordinal = 1;
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorOrdinalParent getBehavior =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of RemoteWebElement";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_ordinalNonExistent() {
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "form");
        final String locatorValue = "label";
        final int ordinal = 4;
        final WebGetElementByLocatorOrdinalParent getBehavior =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        MockView.directNav();
        final WebElement actual = getBehavior.execute();
        final String message = "Failed to return null when ordinal > list size";
        Assert.assertNull(actual, message);
    }
}
