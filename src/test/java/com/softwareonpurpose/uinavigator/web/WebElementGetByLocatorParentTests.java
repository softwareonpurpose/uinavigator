package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentTests extends TestClass {
    @Test
    public void testConstructor_bodyTagLocatorNullParent() {
        UiHost host = UiHost.getInstance();
        String description = "Body";
        final String locatorValue = "body";
        final WebElementGet getParent = null;
        Class<WebElementGetByLocatorParent> expected = WebElementGetByLocatorParent.class;
        //noinspection ConstantConditions,rawtypes
        Class actual = WebElementGetByLocatorParent
                .getInstance(description, UiLocatorType.TAG, locatorValue, getParent, host).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagLocatorNullParent() {
        UiHost host = UiHost.getInstance();
        String description = "Select";
        final String locatorValue = "select";
        final WebElementGet getParent = null;
        Class<WebElementGetByLocatorParent> expected = WebElementGetByLocatorParent.class;
        //noinspection ConstantConditions
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.TAG, locatorValue, getParent, host);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_nameLocatorNullParent() {
        UiHost host = UiHost.getInstance();
        String description = "Username";
        final String locatorValue = "user_name";
        final WebElementGet getParent = null;
        Class<WebElementGetByLocatorParent> expected = WebElementGetByLocatorParent.class;
        //noinspection ConstantConditions
        final WebElementGetByLocatorParent getElement = WebElementGetByLocatorParent
                .getInstance(description, UiLocatorType.NAME, locatorValue, getParent, host);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Username";
        final String locatorValue = "user_name";
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
