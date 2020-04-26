package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementClickTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testClick() {
        String expected = "Clicked";
        WebUiElement element = WebUiElement.getInstance("Button", UiLocatorType.ID, "button-1");
        MockView.directNav();
        element.click();
        String actual = element.getText();
        Assert.assertEquals(actual, expected, "Failed to click button2");
    }
}
