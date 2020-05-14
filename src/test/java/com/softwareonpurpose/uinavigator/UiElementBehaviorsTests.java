package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGet;
import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import com.softwareonpurpose.uinavigator.web.WebHost;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsTests {
    @Test
    public void testGetInstanceByLocator_tagSelect() {
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = UiElementBehaviors.getInstanceByLocator("Select", UiLocatorType.TAG, "select").getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocator_idName() {
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = UiElementBehaviors.getInstanceByLocator("Name", UiLocatorType.ID, "name").getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinal() {
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinal("Select", UiLocatorType.TAG, "select", attribute, attributeValue, ordinal);
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        final String description = "Form";
        final String locatorValue = "form";
        WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent("Select",
                        UiLocatorType.TAG, "select", attribute, attributeValue,
                        ordinal, getParent);
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testIsDisplayed() {
        final String attribute = "for";
        final String attributeValue = "name";
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttribute("Label", UiLocatorType.TAG, "label", attribute, attributeValue);
        MockView.directNav();
        boolean actual = behaviors.isDisplayed();
        WebHost.quitInstance();
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
        final String parentDescription = "Form";
        final String locatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue);
        final String description = "Element";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(
                        description, UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, getParent);
        String expected = "{\"description\":\"Element\",\"getElement\":{\"attributeValue\":\"select-element\",\"attribute\":\"data-test\",\"ordinal\":2,\"getParent\":{\"locator\":{\"tagName\":\"form\"},\"description\":\"Form\"},\"locator\":{\"tagName\":\"select\"},\"description\":\"Element\"}}";
        String actual = behaviors.toString();
        Assert.assertEquals(actual, expected, "Failed to return description via toString()");
    }
}
