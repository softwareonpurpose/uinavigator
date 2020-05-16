package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebUiRegionTests extends TestClass {
    @Test
    public void testGetElement() {
        UiHost host = UiHost.getInstance();
        UiElement expected = UiElement.getInstance("Form", UiLocatorType.TAG, "form", host);
        UiRegion region = MockRegion.getInstance(expected);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        host.load(uri);
        UiElement actual = region.getElement();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebUiElement");
    }

    @Test
    public void testSuppressLogging() {
        UiHost host = UiHost.getInstance();
        UiElement regionElement = UiElement.getInstance("Form", UiLocatorType.TAG, "form", host);
        UiRegion.suppressConstructionLogging(true);
        Class<UiRegion> expected = UiRegion.class;
        //noinspection rawtypes
        Class actual = MockRegion.getInstance(regionElement).getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to construct WebUiRegion when logging suppressed");
    }
}
