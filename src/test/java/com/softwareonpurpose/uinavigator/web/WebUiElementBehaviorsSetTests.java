package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementBehaviorsSetTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testSet() {
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocator(new By.ByName("user_name"));
        behaviors.set("Hello World");
        String expected = "Hello World";
        String actual = behaviors.getText();
        WebUiHost.quitInstance();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }
}
