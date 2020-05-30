package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElementGet;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementAttributeSetTests {
    @SuppressWarnings("RedundantSuppression")
    @Test
    public void testConstructor() {
        //noinspection rawtypes
        Class expected = WebElementAttributeSet.class;
        String description = "Button";
        final String locatorValue = "button-1";
        UiHost host = UiHost.getInstance();
        UiElementGet getBehavior = WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        //noinspection rawtypes,InstantiatingObjectToGetClassObject
        Class actual = new WebElementAttributeSet(getBehavior, host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElementAttributeSet");
    }
}
