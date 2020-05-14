package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorBodyTagTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute_tagBody() {
        String description = "Body Tag";
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebHost.getInstance().load(uri);
        final String locatorValue = "body";
        WebGetElementListByLocator listBehavior =
                WebGetElementListByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
