package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetAttributeNullArgumentTests extends TestClass {
    @Test
    public void testExecute_nullArgument() {
        UiHost host = UiHost.getInstance();
        final String locatorValue = "empty-select-two";
        final String description = "Select";
        UiElementGet getBehavior =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        MockView.directNav(host);
        String actual = UiElementGetAttribute.getInstance(getBehavior).execute(null);
        host.quit();
        Assert.assertNull(actual, "Failed to return null when requested attribute is null");
    }
}
