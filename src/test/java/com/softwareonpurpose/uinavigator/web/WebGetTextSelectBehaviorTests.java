package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetTextBehavior;
import com.softwareonpurpose.uinavigator.UiLocatorType;
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
        WebGetElementBehavior get = WebGetElementByLocator.getInstance(UiLocatorType.ID, "empty-select");
        GetTextBehavior getText = WebGetTextSelectBehavior.getSelectInstance(get);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        String expected = null;
        WebUiHost.getInstance().load(uri);
        String actual = getText.execute();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, message);
    }
}
