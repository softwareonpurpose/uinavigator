package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalTests extends TestClass {
    @Test
    public void testExecute() {
        host = UiHost.getInstance();
        String description = "Option";
        final String locatorValue = "option";
        final int ordinal = 5;
        final WebElementGet getElement =
                WebElementGetByLocatorOrdinal.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, UiDriverGet.getInstance());
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement");
    }
}
