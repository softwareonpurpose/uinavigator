package com.craigstockton.uinavigator;

import com.craigstockton.uinavigator.driverinstantiation.FirefoxInstantiation;
import com.craigstockton.uinavigator.validators.BooleanValidator;
import com.craigstockton.uinavigator.validators.ClassValidator;
import com.craigstockton.uinavigator.validators.IntegerValidator;
import com.craigstockton.uinavigator.validators.StringValidator;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

@Test
public class UiHostTest extends TestBase {

    private static final String uri = "http://www.google.com";

    @Test
    public void getDefaultInstance() {
        Class expected = UiHost.class;
        Class actual = UiHost.getInstance().getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void load() {
        String domain = "www.google.com";
        String expected = String.format("https://%s", domain);
        UiHost.getInstance().load(String.format("http://%s", domain));
        String actual = UiHost.getInstance().getUri();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void findUiElement() {
        String expected = "ctr-p";
        final UiHost browser = UiHost.getInstance();
        browser.load("http://www.google.com");
        String actual = browser.findUiElement(By.id("viewport")).getAttribute("class");
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void findUiElements() {
        Integer expected = 6;
        final UiHost browser = UiHost.getInstance();
        browser.load(uri);
        Integer actual = browser.findUiElements(By.className("_Gs")).size();
        confirm(IntegerValidator.getInstance(expected, actual).validate());
    }

    @Test( /*
            groups = "under_development"//*/)
    public void waitUntilVisible() {
        UiHost browser = UiHost.getInstance();
        browser.load(uri);
        Boolean actual = browser.waitUntilVisible(By.name("btnK"));
        confirm(BooleanValidator.getInstance(true, actual).validate());
    }

    @Test( /*
            groups = "under_development"//*/)
    public void getSpecifWebDriverInstance() {
        String expected = "Firefox";
        DriverInstantiation driverInstantiation = FirefoxInstantiation.getInstance();
        String actual = UiHost.getInstance(driverInstantiation).getDriverName();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test( //*
            groups = "under_development"//*/
    )
    public void execute() {
        UiHost browser = UiHost.getInstance();
        browser.execute("alert('hello world');");
        confirm("failure");
    }
}
