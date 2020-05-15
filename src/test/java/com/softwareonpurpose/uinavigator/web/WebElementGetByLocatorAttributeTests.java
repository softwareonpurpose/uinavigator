package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeTests extends TestClass {
    @Test
    public void testExecute() {
        host = UiHost.getInstance();
        String description = "Name";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final WebElementGetByLocatorAttribute getBehavior =
                WebElementGetByLocatorAttribute
                        .getInstance(description, UiLocatorType.ID, locatorValue, attribute, attributeValue, UiDriverGet.getInstance());
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement instance");
    }
}
