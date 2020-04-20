package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(new By.ByTagName("form"));
        Class expected = WebUiElement.class;
        MockView.directNav();
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebGetListByLocatorAttributeOrdinalParent getBehavior =
                WebGetListByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, getParent);
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }

    @Test
    public void testExecute_parentNull() {
        Class expected = WebUiElement.class;
        MockView.directNav();
        final By.ByTagName locator = new By.ByTagName("body");
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final int ordinal = 1;
        final WebGetListByLocatorAttributeOrdinalParent getBehavior =
                WebGetListByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, null);
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
