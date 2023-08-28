package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockRegion;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiRegionIsVisibleTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testIsVisible() {
        UiElement regionElement = UiElement.getInstance("Form", UiLocatorType.TAG, "form");
        UiRegion region = MockRegion.getInstance(regionElement);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        boolean expected = true;
        boolean actual = region.isVisible();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected WebUiElement");
    }
}
