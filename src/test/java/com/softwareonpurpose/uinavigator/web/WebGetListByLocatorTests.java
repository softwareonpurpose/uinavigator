package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorTests extends TestClass {
    @Test
    public void testConstructor_typeClass() {
        UiHost host = UiHost.getInstance();
        String description = "Any Class";
        Class<WebGetElementListByLocator> expected = WebGetElementListByLocator.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocator.getInstance(description, UiLocatorType.CLASS, "any", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testConstructor_typeId() {
        UiHost host = UiHost.getInstance();
        String description = "Any Id";
        Class<WebGetElementListByLocator> expected = WebGetElementListByLocator.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocator.getInstance(description, UiLocatorType.ID, "any", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testExecute_tagOption() {
        UiHost host = UiHost.getInstance();
        String description = "Option Tag";
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        host.load(uri);
        final String locatorValue = "option";
        WebGetElementListByLocator listBehavior =
                WebGetElementListByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, host);
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = listBehavior.execute().iterator().next().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
