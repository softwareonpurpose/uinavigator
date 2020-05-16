package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        UiHost host = UiHost.getInstance();
        String description = "Non Existent";
        final String locatorValue = "nonexistent";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent, host);
        MockView.directNav(host);
        WebElement actual = getElement.execute();
        host.quit();
        Assert.assertNull(actual, "Failed to return null for non-existent element");
    }
}
