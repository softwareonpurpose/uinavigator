package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGet;
import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsToStringTests extends TestClass {
    @Test
    public void testToString() {
        UiHost host = UiHost.getInstance();
        final String parentDescription = "Form";
        final String locatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue, host);
        final String description = "Element";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(
                        description, UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, getParent, host);
        String expected = "{\"description\":\"Element\",\"getElement\":{\"attributeValue\":\"select-element\",\"attribute\":\"data-test\",\"ordinal\":2,\"getParent\":{\"locator\":{\"tagName\":\"form\"},\"description\":\"Form\"},\"locator\":{\"tagName\":\"select\"},\"description\":\"Element\"}}";
        String actual = behaviors.toString();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return description via toString()");
    }
}
