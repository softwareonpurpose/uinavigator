package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentNullParentTests extends TestClass {
    @Test
    public void testExecute_nullParent() {
        host = UiHost.getInstance();
        String description = "Username";
        final String locatorValue = "user_name";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent, UiDriverGet.getInstance());
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }
}
