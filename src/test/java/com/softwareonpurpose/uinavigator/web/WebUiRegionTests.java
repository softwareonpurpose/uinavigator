package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockRegion;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiRegionTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetElement() {
        WebUiElement expected = WebUiElement.getInstance("Form", new By.ByTagName("form"));
        WebUiRegion region = MockRegion.getInstance(expected);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        WebUiElement actual = region.getElement();
        Assert.assertEquals(actual, expected, "Failed to return expected WebUiElement");
    }

    @Test
    public void testSuppressLogging() {
        WebUiElement regionElement = WebUiElement.getInstance("Form", new By.ByTagName("form"));
        Class expected = WebUiRegion.class;
        WebUiRegion.suppressConstructionLogging(true);
        Class actual = MockRegion.getInstance(regionElement).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed to construct WebUiRegion when logging suppressed");
    }

    @Test
    public void testIsVisible() {
        WebUiElement regionElement = WebUiElement.getInstance("Form", new By.ByTagName("form"));
        WebUiRegion region = MockRegion.getInstance(regionElement);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        boolean expected = true;
        boolean actual = region.isVisible();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected WebUiElement");
    }
}
