package com.softwareonpurpose.uinavigator.web;

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
}
