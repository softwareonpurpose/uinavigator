package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_typeClass() {
        String description = "Any Class";
        Class<WebGetListByLocator> expected = WebGetListByLocator.class;
        //noinspection rawtypes
        Class actual = WebGetListByLocator.getInstance(description, UiLocatorType.CLASS, "any").getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testConstructor_typeId() {
        String description = "Any Id";
        Class<WebGetListByLocator> expected = WebGetListByLocator.class;
        //noinspection rawtypes
        Class actual = WebGetListByLocator.getInstance(description, UiLocatorType.ID, "any").getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testExecute_tagOption() {
        String description = "Option Tag";
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        final String locatorValue = "option";
        WebGetListByLocator listBehavior =
                WebGetListByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        Class<WebUiElement> expected = WebUiElement.class;
        //noinspection rawtypes
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
