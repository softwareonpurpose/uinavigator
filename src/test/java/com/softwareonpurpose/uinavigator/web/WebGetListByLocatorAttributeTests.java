package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        Class expected = WebUiElement.class;
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final WebGetListByLocatorAttribute getBehavior =
                WebGetListByLocatorAttribute.getInstance(locator, attribute, attributeValue);
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of at least one WebUiElement");
    }

    @Test
    public void testExecute_nonExistent() {
        MockView.directNav();
        int expected = 0;
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "not-there";
        final WebGetListByLocatorAttribute getBehavior =
                WebGetListByLocatorAttribute.getInstance(locator, attribute, attributeValue);
        final int actual = getBehavior.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return null when element is non-existent");
    }
}
