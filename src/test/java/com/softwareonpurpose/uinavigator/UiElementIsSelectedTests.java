package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementIsSelectedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testIsSelected() {
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "button-1");
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
