package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    public void testExecute() {
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(new By.ByTagName("form"));
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final WebGetListByLocatorAttributeParent getBehavior =
                WebGetListByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, getParent);
        Class expected = WebUiElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }

    public void testExecute_nullParent() {
        final By.ByTagName locator = new By.ByTagName("body");
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final WebGetListByLocatorAttributeParent getBehavior =
                WebGetListByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, null);
        Class expected = WebUiElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
