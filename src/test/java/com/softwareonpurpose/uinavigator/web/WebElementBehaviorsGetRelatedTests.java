package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ElementBehaviors;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

@Test
public class WebElementBehaviorsGetRelatedTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGet() {
        ElementBehaviors behaviors = WebElementBehaviors.getInstanceByLocator("Page", UiLocatorType.TAG, "body");
        Object actual = behaviors.get();
        String message = "Failed to return a Selenium RemoteWebElement";
        Assert.assertEquals(actual.getClass(), RemoteWebElement.class, message);
    }

    @Test
    public void testGetList() {
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        ElementBehaviors behaviors = WebElementBehaviors.getInstanceByLocator("Option", UiLocatorType.TAG, "option");
        WebUiHost.getInstance().load(uri);
        Class<ArrayList> expected = ArrayList.class;
        Class actual = behaviors.getList().getClass();
        String message = "Failed to a collection of Selenium RemoteWebElement";
        Assert.assertEquals(actual, expected, message);
    }
}
