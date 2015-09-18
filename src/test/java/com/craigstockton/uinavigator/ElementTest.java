package com.craigstockton.uinavigator;

import com.craigstockton.uinavigator.validators.ClassValidator;
import com.craigstockton.uinavigator.validators.StringValidator;
import org.testng.annotations.Test;

@Test
public class ElementTest extends TestBase {

    @Test
    public void getRootInstance() {
        final Class expected = Element.class;
        Host.getInstance().load("http://www.google.com");
        final Class actual = Element.getInstance("Element", Element.LocatorType.ID, "viewport").getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildInstance() {
        Class expected = Element.class;
        Host.getInstance().load("http://www.google.com");
        Element parent = Element.getInstance("Parent Element", Element.LocatorType.ID, "gsr");
        Element child = Element.getInstance("Child Element", Element.LocatorType.ID, "viewport", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildOrdinalInstance() {
        Class expected = Element.class;
        Host.getInstance().load("http://www.google.com");
        Element parent = Element.getInstance("Parent Element", Element.LocatorType.ID, "gsr");
        Element child = Element.getInstance("Child Element", Element.LocatorType.CLASS, "_Gs", 3, parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildAttributeInstance() {
        Class expected = Element.class;
        Host.getInstance().load("http://www.google.com");
        Element parent = Element.getInstance("Parent Element", Element.LocatorType.ID, "gsr");
        Element child = Element
                .getInstance("Child Element", Element.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildOrdinalGetText() {
        String expected = "Settings";
        Host.getInstance().load("http://www.google.com");
        Element parent = Element.getInstance("Parent Element", Element.LocatorType.ID, "gsr");
        final Element child = Element.getInstance("Child Element", Element.LocatorType.CLASS, "_Gs", 3, parent);
        String actual = child.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeHref() {
        String expected = "https://mail.google.com/mail/?tab=wm";
        Host.getInstance().load("http://www.google.com");
        Element parent = Element.getInstance("Parent Element", Element.LocatorType.ID, "gsr");
        Element child = Element
                .getInstance("Child Element", Element.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        String actual = child.getHref();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeTip() {
        String expected = "Google Apps";
        Host.getInstance().load("http://www.google.com");
        Element parent = Element.getInstance("Parent Element", Element.LocatorType.ID, "gsr");
        Element child = Element
                .getInstance("Child Element", Element.LocatorType.CLASS, "gb_pa", "data-ved", "0CBYQvSc", parent);
        String actual = child.getTip();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void set() {
        String setText = "Search phrase";
        Host.getInstance().load("http://www.google.com");
        String expected = setText;
        final Element searchTextbox = Element.getInstance("Search Box", Element.LocatorType.NAME, "q");
        searchTextbox.set(setText);
        String actual = searchTextbox.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void click() {
        String expected = null;
        Host.getInstance().load("http://www.google.com");
        Element parent = Element.getInstance("'Images' link", Element.LocatorType.CLASS, "gb_Lc");
        Element.getInstance("'Images' link", Element.LocatorType.CLASS, "gb_ua", 3, parent).click();
        parent = Element.getInstance("'Images' link", Element.LocatorType.CLASS, "gb_Lc");
        Element actualElement = Element.getInstance("'Images' link", Element.LocatorType.CLASS, "gb_ua", 3, parent);
        String actual = actualElement == null ? null : actualElement.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }
}
