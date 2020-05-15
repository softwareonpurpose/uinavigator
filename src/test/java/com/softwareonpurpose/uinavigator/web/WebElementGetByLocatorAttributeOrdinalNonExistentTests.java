package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeOrdinalNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        MockView.directNav(host);
        final String attribute = "data-test";
        final int ordinal = 3;
        final String attributeValue = "select";
        String description = "Select";
        final WebElementGetByLocatorAttributeOrdinal getBehavior =
                WebElementGetByLocatorAttributeOrdinal
                        .getInstance(description, UiLocatorType.TAG, attributeValue, attribute, attributeValue, ordinal, UiDriverGet.getInstance());
        final WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is nonexistent");
    }
}
