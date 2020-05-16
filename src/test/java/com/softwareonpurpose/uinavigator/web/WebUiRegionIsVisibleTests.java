package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebUiRegionIsVisibleTests extends TestClass {
    @Test
    public void testIsVisible() {
        UiHost host = UiHost.getInstance();
        UiElement regionElement = UiElement.getInstance("Form", UiLocatorType.TAG, "form", host);
        UiRegion region = MockRegion.getInstance(regionElement);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        host.load(uri);
        boolean expected = true;
        boolean actual = region.isVisible();
        host.quit();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected WebUiElement");
    }
}
