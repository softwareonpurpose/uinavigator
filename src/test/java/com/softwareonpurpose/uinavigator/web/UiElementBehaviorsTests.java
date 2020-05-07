package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElementBehaviors;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

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
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "form");
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
        String locatorString = "\"locator\":{\"tagName\":\"select\"}";
        String getParentString = "\"getParent\":{\"locator\":{\"tagName\":\"form\"}}";
        String elementString =
                String.format("\"attributeValue\":\"select-element\",\"attribute\":\"data-test\",\"ordinal\":2,%s,%s",
                        getParentString, locatorString);
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "form");
        final String description = "Element";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(
                        description, UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, getParent);
        String expected = String.format("{\"getElement\":{%s}}", elementString);
        String actual = behaviors.toString();
        Assert.assertEquals(actual, expected, "Failed to return description via toString()");
    }
}
