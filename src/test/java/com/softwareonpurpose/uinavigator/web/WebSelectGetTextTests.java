package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiElementGetText;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebSelectGetTextTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String description = "Select";
        String message = "Failed to return null when 'select' element has NO 'option' elements";
        final String locatorValue = "empty-select";
        WebElementGet get = WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        UiElementGetText getText = WebSelectGetText.getSelectInstance(get);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        String expected = null;
        host.load(uri);
        String actual = getText.execute();
        host.quit();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, message);
    }
}
