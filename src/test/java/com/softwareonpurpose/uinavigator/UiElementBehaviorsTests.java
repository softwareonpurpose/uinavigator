package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsTests {
    @AfterMethod
    public void terminate(){
        WebUiHost.quitInstance();
    }
    @Test
    public void testGetInstance_default() {
        String expected = "selenium";
        String actual = UiElementBehaviors.getInstance(UiLocatorType.TAG, "body").getType();
        Assert.assertEquals(actual, expected, "Failed to return 'default' behaviors");
    }

    @Test
    public void testGetInstance_select() {
        String expected = "selenium";
        String actual = UiElementBehaviors.getInstance(UiLocatorType.TAG, "select").getType();
        Assert.assertEquals(actual, expected, "Failed to return 'select' behaviors");
    }

    @Test
    public void testGetInstance_name() {
        String expected = "selenium";
        String actual = UiElementBehaviors.getInstance(UiLocatorType.NAME, "select").getType();
        Assert.assertEquals(actual, expected, "Failed to return 'select' behaviors");
    }

    @Test
    public void testGet_web() {
        UiElementBehaviors behaviors = UiElementBehaviors.getInstance(UiLocatorType.TAG, "body");
        Object actual = behaviors.get();
        String message = "Failed to return a Selenium RemoteWebElement";
        Assert.assertEquals(actual.getClass(), RemoteWebElement.class, message);
    }

    @Test
    public void testGetText_web() {
        UiElementBehaviors behaviors = UiElementBehaviors.getInstance(UiLocatorType.TAG, "body");
        String expected = "";
        String actual = behaviors.getText();
        WebUiHost.quitInstance();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testSet_web() {
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        UiElementBehaviors behaviors = UiElementBehaviors.getInstance(UiLocatorType.NAME, "user_name");
        behaviors.set("Hello World");
        String expected = "Hello World";
        String actual = behaviors.getText();
        WebUiHost.quitInstance();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }
}
