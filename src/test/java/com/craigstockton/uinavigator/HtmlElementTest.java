package com.craigstockton.uinavigator;

import com.craigstockton.uinavigator.validators.ClassValidator;
import com.craigstockton.uinavigator.validators.StringValidator;
import org.testng.annotations.Test;

@Test
public class HtmlElementTest extends TestBase {

    @Test
    public void getRootInstance() {
        final Class expected = HtmlElement.class;
        BrowserHost.getInstance().load("http://www.google.com");
        final Class actual = HtmlElement.getInstance("HtmlElement", HtmlElement.LocatorType.ID, "viewport").getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildInstance() {
        Class expected = HtmlElement.class;
        BrowserHost.getInstance().load("http://www.google.com");
        HtmlElement parent = HtmlElement.getInstance("Parent Element", HtmlElement.LocatorType.ID, "gsr");
        HtmlElement child = HtmlElement.getInstance("Child Element", HtmlElement.LocatorType.ID, "viewport", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildOrdinalInstance() {
        Class expected = HtmlElement.class;
        BrowserHost.getInstance().load("http://www.google.com");
        HtmlElement parent = HtmlElement.getInstance("Parent Element", HtmlElement.LocatorType.ID, "gsr");
        HtmlElement child = HtmlElement.getInstance("Child Element", HtmlElement.LocatorType.CLASS, "_Gs", 3, parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildAttributeInstance() {
        Class expected = HtmlElement.class;
        BrowserHost.getInstance().load("http://www.google.com");
        HtmlElement parent = HtmlElement.getInstance("Parent Element", HtmlElement.LocatorType.ID, "gsr");
        HtmlElement child = HtmlElement
                .getInstance("Child Element", HtmlElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        Class actual = child.getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void getChildOrdinalGetText() {
        String expected = "Settings";
        BrowserHost.getInstance().load("http://www.google.com");
        HtmlElement parent = HtmlElement.getInstance("Parent Element", HtmlElement.LocatorType.ID, "gsr");
        final HtmlElement child = HtmlElement.getInstance("Child Element", HtmlElement.LocatorType.CLASS, "_Gs", 3, parent);
        String actual = child.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeHref() {
        String expected = "https://mail.google.com/mail/?tab=wm";
        BrowserHost.getInstance().load("http://www.google.com");
        HtmlElement parent = HtmlElement.getInstance("Parent Element", HtmlElement.LocatorType.ID, "gsr");
        HtmlElement child = HtmlElement
                .getInstance("Child Element", HtmlElement.LocatorType.CLASS, "gb_ua", "data-ved", "0CBQQwi4oAA", parent);
        String actual = child.getHref();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test(enabled = false) //  element is now hidden on Google.com
    public void getChildAttributeTip() {
        String expected = "Google Apps";
        BrowserHost.getInstance().load("http://www.google.com");
        HtmlElement parent = HtmlElement.getInstance("Parent Element", HtmlElement.LocatorType.ID, "gsr");
        HtmlElement child = HtmlElement
                .getInstance("Child Element", HtmlElement.LocatorType.CLASS, "gb_pa", "data-ved", "0CBYQvSc", parent);
        String actual = child.getTip();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void set() {
        String setText = "Search phrase";
        BrowserHost.getInstance().load("http://www.google.com");
        String expected = setText;
        final HtmlElement searchTextbox = HtmlElement.getInstance("Search Box", HtmlElement.LocatorType.NAME, "q");
        searchTextbox.set(setText);
        String actual = searchTextbox.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void click() {
        String expected = null;
        BrowserHost.getInstance().load("http://www.google.com");
        HtmlElement parent = HtmlElement.getInstance("'Images' link", HtmlElement.LocatorType.CLASS, "gb_Lc");
        HtmlElement.getInstance("'Images' link", HtmlElement.LocatorType.CLASS, "gb_ua", 3, parent).click();
        parent = HtmlElement.getInstance("'Images' link", HtmlElement.LocatorType.CLASS, "gb_Lc");
        HtmlElement actualElement = HtmlElement.getInstance("'Images' link", HtmlElement.LocatorType.CLASS, "gb_ua", 3, parent);
        String actual = actualElement == null ? null : actualElement.getText();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }
}
