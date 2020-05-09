package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    public void testExecute() {
        MockView.directNav();
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final String locatorValue = "select";
        final WebUiGetElementListByLocatorAttributeOrdinal getBehavior =
                WebUiGetElementListByLocatorAttributeOrdinal.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal);
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }
}
