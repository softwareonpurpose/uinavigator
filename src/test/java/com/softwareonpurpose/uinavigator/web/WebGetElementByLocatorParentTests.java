package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @SuppressWarnings("RedundantSuppression")
    @Test
    public void testConstructor_bodyTagLocatorNullParent() {
        final String locatorValue = "body";
        final WebGetElementBehavior getParent = null;
        Class<WebGetElementByLocatorParent> expected = WebGetElementByLocatorParent.class;
        //noinspection ConstantConditions,rawtypes
        Class actual = WebGetElementByLocatorParent.getInstance(UiLocatorType.TAG, locatorValue, getParent).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagLocatorNullParent() {
        final String locatorValue = "select";
        final WebGetElementBehavior getParent = null;
        Class<WebGetElementByLocatorParent> expected = WebGetElementByLocatorParent.class;
        //noinspection ConstantConditions
        final WebGetElementByLocatorParent getElement =
                WebGetElementByLocatorParent.getInstance(UiLocatorType.TAG, locatorValue, getParent);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_nameLocatorNullParent() {
        final String locatorValue = "user_name";
        final WebGetElementBehavior getParent = null;
        Class<WebGetElementByLocatorParent> expected = WebGetElementByLocatorParent.class;
        //noinspection ConstantConditions,rawtypes
        Class actual = WebGetElementByLocatorParent.getInstance(UiLocatorType.NAME, locatorValue, getParent).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute() {
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "form");
        final String locatorValue = "user_name";
        final WebGetElementByLocatorParent getElement =
                WebGetElementByLocatorParent.getInstance(UiLocatorType.NAME, locatorValue, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_nullParent() {
        final String locatorValue = "user_name";
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorParent getElement =
                WebGetElementByLocatorParent.getInstance(UiLocatorType.NAME, locatorValue, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }
}
