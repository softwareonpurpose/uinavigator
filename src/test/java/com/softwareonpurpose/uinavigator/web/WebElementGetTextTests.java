package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiElementGetText;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetTextTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String description = "Non Existent";
        final String locatorValue = "nonexistent";
        WebElementGet getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        UiElementGetText getText = WebElementGetText.getInstance(getElement);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        String expected = null;
        host.load(uri);
        String actual = getText.execute();
        final String message = "Failed to return null for attempt to get text of non-existent element";
        host.quit();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, message);
    }
}
