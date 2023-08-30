package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    @Test
    public void isDisplayed() {
        boolean expected = true;
        UiNavigator.load("https://www.w3schools.com/html/html_examples.asp");
        boolean actual = UiNavigator.getElement("'Tutorials' nav button", UiLocatorType4.ID, "navbtn_tutorials").isDisplayed();
        Assert.assertEquals(actual, expected);
        UiNavigator.quitInstance();
    }
}
