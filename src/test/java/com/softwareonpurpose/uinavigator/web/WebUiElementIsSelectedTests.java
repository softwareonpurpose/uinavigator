package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementIsSelectedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testIsSelected() {
        WebUiElement element = WebUiElement.getInstance("Element", UiLocatorType.ID, "button-1");
        String attribute = "data-selected";
        String value = "true";
        element = element.setSelectedBehavior(attribute, value);
        boolean expected = true;
        MockView.directNav();
        boolean actual = element.isSelected();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected state");
    }
}
