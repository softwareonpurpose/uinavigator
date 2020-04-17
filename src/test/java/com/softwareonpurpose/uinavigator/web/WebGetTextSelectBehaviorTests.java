package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetTextSelectBehaviorTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String message = "Failed to return null when 'select' element has NO 'option' elements";
        WebGetElementBehavior get = WebGetElementProvider.getInstance(new By.ById("empty-select"));
        WebGetTextSelectBehavior getText = WebGetTextSelectBehavior.getInstance(get);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        String expected = null;
        WebUiHost.getInstance().load(uri);
        String actual = getText.execute();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, message);
    }
}
