package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ElementBehaviors;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class ElementBehaviorsTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetInstanceByLocator_tagSelect() {
        Class<ElementBehaviors> expected = ElementBehaviors.class;
        //noinspection rawtypes
        Class actual = ElementBehaviors.getInstanceByLocator("Select", UiLocatorType.TAG, "select").getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocator_idName() {
        Class<ElementBehaviors> expected = ElementBehaviors.class;
        //noinspection rawtypes
        Class actual = ElementBehaviors.getInstanceByLocator("Name", UiLocatorType.ID, "name").getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinal() {
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeOrdinal("Select", UiLocatorType.TAG, "select", attribute, attributeValue, ordinal);
        Class<ElementBehaviors> expected = ElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        final String description = "Form";
        final String locatorValue = "form";
        WebUiGetElementByLocator getParent =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeOrdinalParent("Select",
                        UiLocatorType.TAG, "select", attribute, attributeValue,
                        ordinal, getParent);
        Class<ElementBehaviors> expected = ElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testIsDisplayed() {
        final String attribute = "for";
        final String attributeValue = "name";
        final ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttribute("Label", UiLocatorType.TAG, "label", attribute, attributeValue);
        MockView.directNav();
        boolean actual = behaviors.isDisplayed();
        Assert.assertTrue(actual, "Failed to return 'true' for existing element");
    }

    @Test
    public void testSuppressLogging() {
        boolean expected = true;
        //noinspection ConstantConditions
        ElementBehaviors.suppressLogging(expected);
        boolean actual = ElementBehaviors.isLoggingSuppressed();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to set logging suppression");
    }

    @Test
    public void testToString() {
        final String parentDescription = "Form";
        final String locatorValue = "form";
        WebUiGetElement getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue);
        final String description = "Element";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(
                        description, UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, getParent);
        String expected = "{\"description\":\"Element\",\"getElement\":{\"attributeValue\":\"select-element\",\"attribute\":\"data-test\",\"ordinal\":2,\"getParent\":{\"locator\":{\"tagName\":\"form\"},\"description\":\"Form\"},\"locator\":{\"tagName\":\"select\"},\"description\":\"Element\"}}";
        String actual = behaviors.toString();
        Assert.assertEquals(actual, expected, "Failed to return description via toString()");
    }
}
