package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementClickTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testClick() {
        String expected = "Clicked";
        UiElement element = UiElement.getInstance("Button", UiLocatorType.ID, "button-1");
        MockView.directNav();
        element.click();
        String actual = element.getText();
        Assert.assertEquals(actual, expected, "Failed to click button2");
    }
}
