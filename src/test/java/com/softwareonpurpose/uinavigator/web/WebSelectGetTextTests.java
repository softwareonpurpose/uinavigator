package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ElementGetText;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebSelectGetTextTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String description="Select";
        String message = "Failed to return null when 'select' element has NO 'option' elements";
        final String locatorValue = "empty-select";
        WebUiGetElement get = WebUiGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        ElementGetText getText = WebSelectGetText.getSelectInstance(get);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        String expected = null;
        WebUiHost.getInstance().load(uri);
        String actual = getText.execute();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, message);
    }
}
