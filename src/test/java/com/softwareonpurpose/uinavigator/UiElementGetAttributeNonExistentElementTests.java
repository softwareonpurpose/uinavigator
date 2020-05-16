package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetAttributeNonExistentElementTests extends TestClass {
    @Test
    public void testExecute_nonExistentElement() {
        UiHost host = UiHost.getInstance();
        final String description = "Bogus";
        final String locatorValue = "bogus";
        UiElementGet getBehavior =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        MockView.directNav(host);
        String actual = UiElementGetAttribute.getInstance(getBehavior).execute(null);
        host.quit();
        Assert.assertNull(actual, "Failed to return null when element non-existent");
    }
}
