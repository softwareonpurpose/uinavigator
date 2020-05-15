package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import com.softwareonpurpose.uinavigator.web.WebElementIsActive;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class IsActiveBehaviorTests {
    @Test
    public void testGetInstance() {
        //noinspection rawtypes
        Class expected = WebElementIsActive.class;
        final String description = "Pet Select";
        final String locatorValue = "pet-select";
        final UiDriverGet getDriver = UiDriverGet.getInstance();
        UiElementGet getElement = WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, getDriver);
        String attribute = "data-state";
        String value = "active";
        //noinspection rawtypes
        Class actual = UiElementIsActive.getInstance(getElement, attribute, value, getDriver).getClass();
        Assert.assertEquals(actual, expected, "Failed to instantiate an instance of IsActiveBehavior");
    }
}
