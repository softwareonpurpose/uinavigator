package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementClickTests extends TestClass {
    @Test
    public void testClick() {
        UiHost host = UiHost.getInstance();
        String expected = "Clicked";
        UiElement element = UiElement.getInstance("Button", UiLocatorType.ID, "button-1", host);
        MockView.directNav(host);
        element.click();
        String actual = element.getText();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to click button2");
    }
}
