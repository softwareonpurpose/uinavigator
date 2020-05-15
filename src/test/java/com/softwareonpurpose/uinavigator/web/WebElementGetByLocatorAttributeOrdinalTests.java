package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeOrdinalTests extends TestClass {
    @Test
    public void testExecute() {
        host = UiHost.getInstance();
        String description = "Select";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final String locatorValue = "select";
        final WebElementGetByLocatorAttributeOrdinal getBehavior =
                WebElementGetByLocatorAttributeOrdinal.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal, UiDriverGet.getInstance());
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }
}
