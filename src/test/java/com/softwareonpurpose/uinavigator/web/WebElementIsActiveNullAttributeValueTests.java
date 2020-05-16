package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementIsActiveNullAttributeValueTests extends TestClass {
    @Test
    public void testExecute_nullValue() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        final String description = "Element";
        final String locatorValue = "user_name";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.NAME, locatorValue, host);
        final String attribute = "data-active";
        final String value = null;
        //noinspection ConstantConditions
        boolean actual = WebElementIsActive.getIsStateInstance(getElement, attribute, value).execute();
        host.quit();
        Assert.assertFalse(actual, "Failed to return false when value null");
    }
}
