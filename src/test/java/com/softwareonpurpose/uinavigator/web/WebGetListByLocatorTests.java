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
        Class<WebGetListByLocator> expected = WebGetListByLocator.class;
        Class actual = WebGetListByLocator.getInstance(UiLocatorType.CLASS, "any").getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testConstructor_typeId() {
        Class<WebGetListByLocator> expected = WebGetListByLocator.class;
        Class actual = WebGetListByLocator.getInstance(UiLocatorType.ID, "any").getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocator");
    }

    @Test
    public void testExecute_tagOption() {
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        WebGetListByLocator listBehavior = WebGetListByLocator.getInstance(UiLocatorType.TAG, "option");
        Class<WebUiElement> expected = WebUiElement.class;
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }

    @Test
    public void testExecute_tagBody() {
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        WebGetListByLocator listBehavior = WebGetListByLocator.getInstance(UiLocatorType.TAG, "body");
        Class<WebUiElement> expected = WebUiElement.class;
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
