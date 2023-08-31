package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    @AfterMethod
    public void terminate() {
        UiNavigator.quitInstance();
    }

    @Test
    public void isDisplayed() {
        boolean expected = true;
        UiHost4.getInstance().load("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_basic_document");
        boolean actual = UiElement4.getInstance("'Tutorials' nav button", UiLocatorType4.TAG, "body").isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
