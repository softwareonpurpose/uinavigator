package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementBehaviorsSetNullTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testSet_null() {
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        UiElement element = UiElement.getInstance("Element", UiLocatorType.NAME, "user_name");
        element.set(null);
        String expected = "";
        String actual = element.getText();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }
}
