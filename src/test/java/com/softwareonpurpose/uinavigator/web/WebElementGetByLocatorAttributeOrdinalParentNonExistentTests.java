package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeOrdinalParentNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        String parentDescription = "Form";
        WebElementGet getParent = WebElementGetByLocator
                .getInstance(parentDescription, UiLocatorType.TAG, "form", UiDriverGet.getInstance());
        String description = "Select";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        MockView.directNav(host);
        final String locatorValue = "select";
        final WebElementGetByLocatorAttributeOrdinalParent getBehavior =
                WebElementGetByLocatorAttributeOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue,
                                attribute, attributeValue, ordinal, getParent, UiDriverGet.getInstance());
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null for non-existent element");
    }
}
