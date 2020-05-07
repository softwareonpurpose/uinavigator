package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetElementBehavior;
import com.softwareonpurpose.uinavigator.IsActiveBehavior;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class IsActiveBehaviorTests {
    @Test
    public void testGetInstance() {
        //noinspection rawtypes
        Class expected = WebIsActiveBehavior.class;
        final String description = "Pet Select";
        final String locatorValue = "pet-select";
        GetElementBehavior getElement = WebGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        String attribute = "data-state";
        String value = "active";
        //noinspection rawtypes
        Class actual = IsActiveBehavior.getInstance(getElement, attribute, value).getClass();
        Assert.assertEquals(actual, expected, "Failed to instantiate an instance of IsActiveBehavior");
    }
}
