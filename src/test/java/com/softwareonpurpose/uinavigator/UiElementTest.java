package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.validators.ClassCalibrator;
import com.softwareonpurpose.uinavigator.validators.StringCalibrator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElementTest extends TestBase {

    @DataProvider
    public static Object[][] toStringScenarios() {
        String simpleDescription = "Simple";
        String withParentDescription = "With Parent";
        String withGrandparentDescription = "With Grandparent";
        String byOrdinalDescription = "By Ordinal";
        String byAttributeDescription = "By Attribute";
        String id = "an_id";
        String clazz = "a_class";
        String name = "a_name";
        String tag = "a_tag";
        String attribute = "attribute";
        String attributeValue = "attribute_value";
        int ordinal = 2;
        UiElement simpleElement = UiElement.getInstance(simpleDescription, UiElement.LocatorType.ID, id);
        UiElement elementWithParent = UiElement.getInstance(withParentDescription, UiElement.LocatorType.CLASS, "a_class", simpleElement);
        UiElement elementWithGrandparent = UiElement.getInstance(withGrandparentDescription, UiElement.LocatorType.NAME, name, elementWithParent);
        UiElement elementByOrdinal = UiElement.getInstance(byOrdinalDescription, UiElement.LocatorType.TAG, "a_tag", ordinal, simpleElement);
        String simpleElementToString = "{\"description\":\"%s\",\"locatorType\":\"%s\",\"locatorValue\":\"%s\"}";
        String elementWithParentToString = "{\"description\":\"%s\",\"locatorType\":\"%s\",\"locatorValue\":\"%s\",\"parent\":%s}";
        String elementWithGrandparentToString = "{\"description\":\"%s\",\"locatorType\":\"%s\",\"locatorValue\":\"%s\",\"parent\":%s}";
        String elementByOrdinalToString = "{\"description\":\"%s\",\"locatorType\":\"%s\",\"locatorValue\":\"%s\",\"ordinal\":%d,\"parent\":%s}";
        String simpleToString = String.format(simpleElementToString, simpleDescription, UiElement.LocatorType.ID, id);
        String withParentToString = String.format(elementWithParentToString, withParentDescription, UiElement.LocatorType.CLASS, clazz, simpleToString);
        String withGrandparentToString = String.format(elementWithGrandparentToString, withGrandparentDescription, UiElement.LocatorType.NAME, name, withParentToString);
        String byOrdinalToString = String.format(elementByOrdinalToString, byOrdinalDescription, UiElement.LocatorType.TAG, tag, ordinal, simpleToString);
        String elementByAttributeToString = "{\"description\":\"%s\",\"locatorType\":\"%s\",\"locatorValue\":\"%s\",\"attribute\":\"%s\",\"attributeValue\":\"%s\",\"parent\":%s}";
        String byAttributeToString = String.format(elementByAttributeToString, byAttributeDescription, UiElement.LocatorType.CLASS, clazz, attribute, attributeValue, simpleToString);
        return new Object[][]{
                {simpleElement, simpleToString},
                {elementWithParent, withParentToString},
                {elementWithGrandparent, withGrandparentToString},
                {elementByOrdinal, byOrdinalToString},
                {UiElement.getInstance(byAttributeDescription, UiElement.LocatorType.CLASS,clazz, attribute, attributeValue, simpleElement), byAttributeToString}
        };
    }

    @Test
    public void getRootInstance() {
        final Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        final Class actual = UiElement.getInstance("UiElement", UiElement.LocatorType.ID, "viewport").getClass();
        confirm(ClassCalibrator.construct(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.ID, "viewport", parent);
        Class actual = child.getClass();
        confirm(ClassCalibrator.construct(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildOrdinalInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.CLASS, "_Gs", 3, parent);
        Class actual = child.getClass();
        confirm(ClassCalibrator.construct(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildAttributeInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        Class actual = child.getClass();
        confirm(ClassCalibrator.construct(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildOrdinalGetText() {
        String expected = "Settings";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        final UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.CLASS, "ctr-p", 3, parent);
        String actual = child.getText();
        confirm(StringCalibrator.construct("Text of child by ordinal", expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeHref() {
        String expected = "https://mail.google.com/mail/?tab=wm";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        String actual = child.getHref();
        confirm(StringCalibrator.construct("Text of child by attribute 'href'", expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeTip() {
        String expected = "Google Apps";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_pa", "data-ved", "0CBYQvSc", parent);
        String actual = child.getTip();
        confirm(StringCalibrator.construct("Text of child by attribute 'tip'", expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void set() {
        String expected = "Search phrase";
        UiHost.getInstance().load("http://www.google.com");
        final UiElement searchTextBox = UiElement.getInstance("Search Box", UiElement.LocatorType.NAME, "q");
        searchTextBox.set(expected);
        String actual = searchTextBox.getText();
        confirm(StringCalibrator.construct("Searchbox value", expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void click() {
        String expected = null;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_Lc");
        UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_ua", 3, parent).click();
        parent = UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_Lc");
        String actual = UiElement.getInstance("'Images' link", UiElement.LocatorType.CLASS, "gb_ua", 3, parent).getText();
        //noinspection ConstantConditions
        confirm(StringCalibrator.construct("Value after click", expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void setAttribute() {
        String expected = "Bogus Alt Text";
        UiHost.getInstance().load("http://www.google.com");
        UiElement element = UiElement.getInstance("Logo", UiElement.LocatorType.ID, "hplogo");
        element.setAttribute("alt", expected);
        String actual = element.getAttribute("alt");
        confirm(StringCalibrator.construct("Attribute value", expected, actual).validate());
    }

    @Test(dataProvider = "toStringScenarios")
    public void to_String(UiElement element, String expected) {
        String actual = element.toString();
        Assert.assertEquals(actual, expected);
    }
}
