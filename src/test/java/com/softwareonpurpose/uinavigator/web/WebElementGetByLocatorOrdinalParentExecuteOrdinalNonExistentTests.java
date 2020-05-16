package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentExecuteOrdinalNonExistentTests extends TestClass {
    @Test
    public void testExecute_ordinalNonExistent() {
        UiHost host = UiHost.getInstance();
        String parentDescription = "Form";
        WebElementGet getParent = WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, "form", host);
        String description = "Label";
        final String locatorValue = "label";
        final int ordinal = 4;
        final WebElementGetByLocatorOrdinalParent getBehavior =
                WebElementGetByLocatorOrdinalParent.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, host);
        MockView.directNav(host);
        final WebElement actual = getBehavior.execute();
        final String message = "Failed to return null when ordinal > list size";
        host.quit();
        Assert.assertNull(actual, message);
    }
}
