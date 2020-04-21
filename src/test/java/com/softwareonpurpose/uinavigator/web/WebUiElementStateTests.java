package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementStateTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testSetActiveBehavior() {
        Class expected = WebUiElement.class;
        String activeValue = "active";
        WebUiElement element = WebUiElement.getInstance("Element", new By.ById("pet-select"));
        element = element.setActiveBehavior("data-state", activeValue);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return WebUiElement when 'active' state behavior set");
    }

    @Test
    public void testIsSelected() {
        WebUiElement element = WebUiElement.getInstance("Element", new By.ById("button-1"));
        String attribute = "data-selected";
        String value = "true";
        element.setSelectedBehavior(attribute, value);
        boolean expected = true;
        MockView.directNav();
        boolean actual = element.isSelected();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected state");
    }
}
