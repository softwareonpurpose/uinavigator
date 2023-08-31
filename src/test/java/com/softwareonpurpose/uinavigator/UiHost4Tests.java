package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiHost4Tests {
    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitInstance();
    }

    @Test
    public void load() {
        UiHost4 host = UiHost4.getInstance();
        Boolean expected = true;
        host.load("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_basic_document");
        Boolean actual = host.getCurrentUrl().contains("https://www.w3schools.com/html/tryit.asp");
        Assert.assertEquals(actual, expected);
    }
}
