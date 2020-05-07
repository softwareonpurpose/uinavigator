package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetTextBehavior;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetTextDefaultBehaviorTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String description = "Non Existent";
        final String locatorValue = "nonexistent";
        WebGetElementBehavior getElement =
                WebGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        GetTextBehavior getText = WebGetTextDefaultBehavior.getInstance(getElement);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        String expected = null;
        WebUiHost.getInstance().load(uri);
        String actual = getText.execute();
        final String message = "Failed to return null for attempt to get text of non-existent element";
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, message);
    }
}
