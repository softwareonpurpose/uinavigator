package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiElementGet;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementAttributeSetExecuteTests {
    @Test
    public void testExecute() {
        String attribute = "data-selected";
        String expected = "false";
        String description = "Button";
        final String locatorValue = "button-1";
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        UiElementGet getBehavior = WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        new WebElementAttributeSet(getBehavior, host).execute(attribute, expected);
        final String actual = new WebElementGetAttribute(getBehavior).execute(attribute);
        host.quit();
        Assert.assertEquals(actual, expected, String.format("Failed to set attribute '%s' to [%s]", attribute, expected));
    }
}
