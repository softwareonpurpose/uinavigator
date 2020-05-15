package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementClickTests extends TestClass {
    @Test
    public void testClick() {
        host = UiHost.getInstance();
        String expected = "Clicked";
        UiElement element = UiElement.getInstance("Button", UiLocatorType.ID, "button-1");
        MockView.directNav(host);
        element.click();
        String actual = element.getText();
        Assert.assertEquals(actual, expected, "Failed to click button2");
    }
}
