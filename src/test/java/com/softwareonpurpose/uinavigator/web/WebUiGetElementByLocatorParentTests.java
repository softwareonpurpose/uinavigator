package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiGetElementByLocatorParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @SuppressWarnings("RedundantSuppression")
    @Test
    public void testConstructor_bodyTagLocatorNullParent() {
        String description = "Body";
        final String locatorValue = "body";
        final WebUiGetElement getParent = null;
        Class<WebUiGetElementByLocatorParent> expected = WebUiGetElementByLocatorParent.class;
        //noinspection ConstantConditions,rawtypes
        Class actual = WebUiGetElementByLocatorParent
                .getInstance(description, UiLocatorType.TAG, locatorValue, getParent).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagLocatorNullParent() {
        String description = "Select";
        final String locatorValue = "select";
        final WebUiGetElement getParent = null;
        Class<WebUiGetElementByLocatorParent> expected = WebUiGetElementByLocatorParent.class;
        //noinspection ConstantConditions
        final WebUiGetElementByLocatorParent getElement =
                WebUiGetElementByLocatorParent.getInstance(description, UiLocatorType.TAG, locatorValue, getParent);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_nameLocatorNullParent() {
        String description = "Username";
        final String locatorValue = "user_name";
        final WebUiGetElement getParent = null;
        Class<WebUiGetElementByLocatorParent> expected = WebUiGetElementByLocatorParent.class;
        //noinspection ConstantConditions
        final WebUiGetElementByLocatorParent getElement = WebUiGetElementByLocatorParent
                .getInstance(description, UiLocatorType.NAME, locatorValue, getParent);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute() {
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebUiGetElement getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        String description = "Username";
        final String locatorValue = "user_name";
        final WebUiGetElementByLocatorParent getElement =
                WebUiGetElementByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }
}
