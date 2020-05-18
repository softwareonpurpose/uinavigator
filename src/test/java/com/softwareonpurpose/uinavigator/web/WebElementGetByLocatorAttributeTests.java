package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String description = "Name";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final WebElementGetByLocatorAttribute getBehavior =
                WebElementGetByLocatorAttribute
                        .getInstance(description, UiLocatorType.ID, locatorValue, attribute, attributeValue, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement instance");
    }

    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        String description = "Name";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final WebElementGetByLocatorAttribute getBehavior =
                WebElementGetByLocatorAttribute
                        .getInstance(description, UiLocatorType.ID, locatorValue, attribute, attributeValue, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        getBehavior.execute();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement instance");
    }
}
