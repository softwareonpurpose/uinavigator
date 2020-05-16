package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGet;
import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsTests extends TestClass {
    @Test
    public void testGetInstanceByLocator_tagSelect() {
        UiHost host = UiHost.getInstance();
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = UiElementBehaviors.getInstanceByLocator("Select", UiLocatorType.TAG, "select", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocator_idName() {
        UiHost host = UiHost.getInstance();
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = UiElementBehaviors.getInstanceByLocator("Name", UiLocatorType.ID, "name", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinal() {
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        UiHost host = UiHost.getInstance();
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinal("Select", UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, host);
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        UiHost host = UiHost.getInstance();
        final String description = "Form";
        final String locatorValue = "form";
        WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, host);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent("Select",
                        UiLocatorType.TAG, "select", attribute, attributeValue,
                        ordinal, getParent, host);
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testIsDisplayed() {
        UiHost host = UiHost.getInstance();
        final String attribute = "for";
        final String attributeValue = "name";
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttribute("Label", UiLocatorType.TAG, "label", attribute, attributeValue, host);
        MockView.directNav(host);
        boolean actual = behaviors.isDisplayed();
        host.quit();
        Assert.assertTrue(actual, "Failed to return 'true' for existing element");
    }

    @Test
    public void testSuppressLogging() {
        boolean expected = true;
        //noinspection ConstantConditions
        UiElementBehaviors.suppressLogging(expected);
        boolean actual = UiElementBehaviors.isLoggingSuppressed();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to set logging suppression");
    }

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
