package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;
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
        WebUiElement expected = WebUiElement.getInstance("Form", UiLocatorType.TAG, "form");
        UiRegion region = MockRegion.getInstance(expected);
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        UiElement actual = region.getElement();
        Assert.assertEquals(actual, expected, "Failed to return expected WebUiElement");
    }

    @Test
    public void testSuppressLogging() {
        WebUiElement regionElement = WebUiElement.getInstance("Form", UiLocatorType.TAG, "form");
        UiRegion.suppressConstructionLogging(true);
        Class<UiRegion> expected = UiRegion.class;
        //noinspection rawtypes
        Class actual = MockRegion.getInstance(regionElement).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed to construct WebUiRegion when logging suppressed");
    }
}
