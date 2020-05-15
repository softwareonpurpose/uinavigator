package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorBodyTagTests extends TestClass {
    @Test
    public void testExecute_tagBody() {
        host = UiHost.getInstance();
        String description = "Body Tag";
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        host.load(uri);
        final String locatorValue = "body";
        WebGetElementListByLocator listBehavior =
                WebGetElementListByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, UiDriverGet.getInstance());
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
