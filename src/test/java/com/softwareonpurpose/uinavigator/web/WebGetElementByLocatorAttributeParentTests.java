package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        final By.ByTagName locator = new By.ByTagName("body");
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, null);
        Class expected = WebGetElementByLocatorAttributeParent.class;
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_idParentNull() {
        final By locator = new By.ById("name");
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, null);
        Class expected = WebGetElementByLocatorAttributeParent.class;
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_parentInstance() {
        final By locator = new By.ById("name");
        final String attribute = "data-test";
        final String attributeValue = "initial";
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(new By.ByTagName("form"));
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, getParent);
        MockView.directNav();
        Class expected = RemoteWebElement.class;
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of WebElement";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_nullParent() {
        MockView.directNav();
        final By locator = new By.ByTagName("body");
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        Class expected = RemoteWebElement.class;
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, null);
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return WebElement instance");
    }
}
