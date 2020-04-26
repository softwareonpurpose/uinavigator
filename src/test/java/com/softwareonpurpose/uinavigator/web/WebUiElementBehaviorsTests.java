package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementBehaviorsTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetInstanceByLocator_tagSelect() {
        Class<WebUiElementBehaviors> expected = WebUiElementBehaviors.class;
        Class actual = WebUiElementBehaviors.getInstanceByLocator("Select", UiLocatorType.TAG, "select").getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocator_idName() {
        Class<WebUiElementBehaviors> expected = WebUiElementBehaviors.class;
        Class actual = WebUiElementBehaviors.getInstanceByLocator("Name", UiLocatorType.ID, "name").getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinal() {
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinal("Select", UiLocatorType.TAG, "select", attribute, attributeValue, ordinal);
        Class<WebUiElementBehaviors> expected = WebUiElementBehaviors.class;
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "form");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent("Select",
                        UiLocatorType.TAG, "select", attribute, attributeValue,
                        ordinal, getParent);
        Class<WebUiElementBehaviors> expected = WebUiElementBehaviors.class;
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testIsDisplayed() {
        final String attribute = "for";
        final String attributeValue = "name";
        final WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttribute("Label", UiLocatorType.TAG, "label", attribute, attributeValue);
        MockView.directNav();
        boolean actual = behaviors.isDisplayed();
        Assert.assertTrue(actual, "Failed to return 'true' for existing element");
    }

    @Test
    public void testGetSrc() {
        WebUiElement element = WebUiElement.getInstance("Element", UiLocatorType.TAG, "img");
        MockView.directNav();
        String expected = "file://missing.jpg/";
        String actual = element.getSrc();
        Assert.assertEquals(actual, expected, "Failed to return expected 'src' value");
    }

    @Test
    public void testSuppressLogging() {
        boolean expected = true;
        //noinspection ConstantConditions
        WebUiElementBehaviors.suppressLogging(expected);
        boolean actual = WebUiElementBehaviors.isLoggingSuppressed();
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
        final WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(
                        description, UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, getParent);
        String expected = String.format("{\"getElement\":{%s}}", elementString);
        String actual = behaviors.toString();
        Assert.assertEquals(actual, expected, "Failed to return description via toString()");
    }
}
