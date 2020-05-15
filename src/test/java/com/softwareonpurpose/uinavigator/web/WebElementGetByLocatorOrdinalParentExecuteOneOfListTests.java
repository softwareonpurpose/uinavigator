package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentExecuteOneOfListTests extends TestClass {
    @Test
    public void testExecute() {
        host = UiHost.getInstance();
        String description = "Select";
        final String parentLocatorValue = "select";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, parentLocatorValue, UiDriverGet.getInstance());
        final String locatorValue = "option";
        final int ordinal = 4;
        final WebElementGetByLocatorOrdinalParent getBehavior =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, UiDriverGet.getInstance());
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of RemoteWebElement";
        Assert.assertEquals(actual, expected, message);
    }
}
