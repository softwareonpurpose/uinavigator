package com.craigstockton.uinavigator;

import com.craigstockton.uinavigator.validators.BooleanValidator;
import com.craigstockton.uinavigator.validators.ClassValidator;
import com.craigstockton.uinavigator.validators.IntegerValidator;
import com.craigstockton.uinavigator.validators.StringValidator;
import com.craigstockton.validator4test.Validator;
import javafx.scene.control.TableColumnBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.swing.text.TableView;

@Test
public class BrowserHostTest extends TestBase {

    private static final String uri = "http://www.google.com";

    @Test
    public void getInstance() {
        Class expected = BrowserHost.class;
        Class actual = BrowserHost.getInstance().getClass();
        confirm(ClassValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void load() {
        String domain = "www.google.com";
        String expected = String.format("https://%s", domain);
        BrowserHost.getInstance().load(String.format("http://%s", domain));
        String actual = BrowserHost.getInstance().getUri();
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void findUiElement() {
        String expected = "ctr-p";
        final BrowserHost browser = BrowserHost.getInstance();
        browser.load("http://www.google.com");
        String actual = browser.findUiElement(By.id("viewport")).getAttribute("class");
        confirm(StringValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void findUiElements() {
        Integer expected = 6;
        final BrowserHost browser = BrowserHost.getInstance();
        browser.load(uri);
        Integer actual = browser.findUiElements(By.className("_Gs")).size();
        confirm(IntegerValidator.getInstance(expected, actual).validate());
    }

    @Test
    public void waitUntilVisible() {
        BrowserHost browser = BrowserHost.getInstance();
        browser.load(uri);
        Boolean actual = browser.waitUntilVisible(By.id("gsri_ok0"));
        confirm(BooleanValidator.getInstance(true, actual).validate());
    }
}
