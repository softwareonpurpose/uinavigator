package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.validators.ClassValidator;
import com.softwareonpurpose.uinavigator.validators.StringCalibrator;
import org.testng.annotations.Test;

@Test
public class UiElementTest extends TestBase {

    @Test
    public void getRootInstance() {
        final Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        final Class actual = UiElement.getInstance("UiElement", UiElement.LocatorType.ID, "viewport").getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.ID, "viewport", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildOrdinalInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.CLASS, "_Gs", 3, parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildAttributeInstance() {
        Class expected = UiElement.class;
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void getChildOrdinalGetText() {
        String expected = "Settings";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        final UiElement child = UiElement.getInstance("Child UiElement", UiElement.LocatorType.CLASS, "ctr-p", 3, parent);
        String actual = child.getText();
        confirm(StringCalibrator.getInstance("Text of child by ordinal", expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeHref() {
        String expected = "https://mail.google.com/mail/?tab=wm";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        String actual = child.getHref();
        confirm(StringCalibrator.getInstance("Text of child by attribute 'href'", expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeTip() {
        String expected = "Google Apps";
        UiHost.getInstance().load("http://www.google.com");
        UiElement parent = UiElement.getInstance("Parent UiElement", UiElement.LocatorType.ID, "gsr");
        UiElement child = UiElement
                .getInstance("Child UiElement", UiElement.LocatorType.CLASS, "gb_pa", "data-ved", "0CBYQvSc", parent);
        String actual = child.getTip();
        confirm(StringCalibrator.getInstance("Text of child by attribute 'tip'", expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void set() {
        String expected = "Search phrase";
        UiHost.getInstance().load("http://www.google.com");
        final UiElement searchTextbox = UiElement.getInstance("Search Box", UiElement.LocatorType.NAME, "q");
        searchTextbox.set(expected);
        String actual = searchTextbox.getText();
        confirm(StringCalibrator.getInstance("Searchbox value", expected, actual).validate());
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
        confirm(StringCalibrator.getInstance("Value after click", expected, actual).validate());
    }

    @Test(dependsOnMethods = "getRootInstance")
    public void setAttribute() {
        String expected = "Bogus Alt Text";
        UiHost.getInstance().load("http://www.google.com");
        UiElement element = UiElement.getInstance("Logo", UiElement.LocatorType.ID, "hplogo");
        element.setAttribute("alt", expected);
        String actual = element.getAttribute("alt");
        confirm(StringCalibrator.getInstance("Attribute value", expected, actual).validate());
    }
}
