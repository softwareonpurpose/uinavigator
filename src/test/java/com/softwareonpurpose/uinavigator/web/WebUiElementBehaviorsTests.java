package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
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
        Class expected = WebUiElementBehaviors.class;
        Class actual = WebUiElementBehaviors.getInstanceByLocator("Select", new By.ByTagName("select")).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocator_idName() {
        Class expected = WebUiElementBehaviors.class;
        Class actual = WebUiElementBehaviors.getInstanceByLocator("Name", new By.ById("name")).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinal() {
        Class expected = WebUiElementBehaviors.class;
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinal("Select", locator, attribute, attributeValue, ordinal);
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        Class expected = WebUiElementBehaviors.class;
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(new By.ByTagName("form"));
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent("Select",
                        locator, attribute, attributeValue,
                        ordinal, getParent);
        Class actual = behaviors.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetText() {
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocator("Page", new By.ByTagName("body"));
        String expected = "";
        String actual = behaviors.getText();
        WebUiHost.quitInstance();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testIsDisplayed() {
        final By.ByTagName locator = new By.ByTagName("label");
        final String attribute = "for";
        final String attributeValue = "name";
        final WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttribute("Label", locator, attribute, attributeValue);
        MockView.directNav();
        boolean actual = behaviors.isDisplayed();
        Assert.assertTrue(actual, "Failed to return 'true' for existing element");
    }
}
