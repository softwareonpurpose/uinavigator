package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetAttributeNullArgumentTests extends TestClass {
    @Test
    public void testExecute_nullArgument() {
        final String locatorValue = "empty-select-two";
        final String description = "Select";
        UiElementGet getBehavior =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, UiDriverGet.getInstance());
        MockView.directNav(host);
        String actual = UiElementGetAttribute.getInstance(getBehavior).execute(null);
        Assert.assertNull(actual, "Failed to return null when requested attribute is null");
    }
}
