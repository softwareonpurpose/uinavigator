package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebSetSelectBehaviorTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        WebGetElementBehavior getElement = WebGetElementProvider.getInstance(UiLocatorType.ID, "pet-select");
        WebGetTextSelectBehavior getText = WebGetTextSelectBehavior.getInstance(getElement);
        WebSetSelectBehavior setText = WebSetSelectBehavior.getInstance(getElement);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        String expected = "Dog";
        WebUiHost.getInstance().load(uri);
        setText.execute(expected);
        String actual = getText.execute();
        Assert.assertEquals(actual, expected, "Failed to set 'select' element to expected value");
    }
}
