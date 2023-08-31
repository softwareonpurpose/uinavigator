package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    @Test
    public void isDisplayed() {
        boolean expected = true;
        UiNavigator.load("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_basic_document");
        boolean actual = UiNavigator.getElement("'Tutorials' nav button", UiLocatorType4.TAG, "body").isDisplayed();
        Assert.assertEquals(actual, expected);
        UiNavigator.quitInstance();
    }
}
