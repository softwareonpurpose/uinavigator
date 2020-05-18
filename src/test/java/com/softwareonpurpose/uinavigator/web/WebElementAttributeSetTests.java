package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
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

    @SuppressWarnings("RedundantSuppression")
    @Test
    public void testExecute() {
        //noinspection rawtypes
        String expected = "false";
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String description = "Button";
        final String locatorValue = "button-1";
        UiElementGet getBehavior =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        UiDriverGet getDriver = UiDriverGet.getInstance();
        //noinspection rawtypes,InstantiatingObjectToGetClassObject
        final WebElementAttributeSet setAttribute = new WebElementAttributeSet(getBehavior, host);
        String attribute = "data-selected";
        String value = "false";
        setAttribute.execute(attribute, value);
        MockView.expect(MockView.class, host);
        String actual = UiElementGetAttribute.getInstance(getBehavior).execute(attribute);
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to set attribute of element");
    }
}
