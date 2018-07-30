package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.validators.StringCalibrator;
import com.softwareonpurpose.uinavigator.validators.webelement.WebElementCalibrator;
import com.softwareonpurpose.uinavigator.validators.webelement.WebElementExpected;
import com.softwareonpurpose.uinavigator.validators.webelements.WebElementsCalibrator;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test
public class UiHostTest extends TestBase {
    private final static String ANCHOR = "a";

    private static final String uri = "http://the-internet.herokuapp.com";

    @Test
    public void construct() {
        Class expected = UiHost.class;
        Class actual = UiHost.getInstance().getClass();
        Assert.assertEquals(actual, expected, "The creation method getInstance returns an instantiated UiHost object");
    }

    @Test(dependsOnMethods = "construct")
    public void load() {
        String domain = "www.google.com";
        String expected = String.format("https://%s", domain);
        UiHost.getInstance().load(String.format("http://%s", domain));
        String actual = UiHost.getInstance().getUri();
        Assert.assertTrue(actual.contains(expected), "The requested URL is loaded successfully in the UI host");
    }

    @Test(dependsOnMethods = "construct")
    public void findUiElement() {
        WebElementExpected expected = WebElementExpected.construct("div", "content", "large-12 columns", null, null);
        UiHost.getInstance().load(uri);
        WebElement actual = (WebElement) UiHost.getInstance().findUiElement("id", "content");
        confirm(WebElementCalibrator.construct(expected, actual).validate());
    }

    @Test(dependsOnMethods = "construct")
    public void findUiElements() {
        List<WebElementExpected> expected = composeExpectedWebElements();
        UiHost.getInstance().load(uri);
        List<WebElement> actual = new ArrayList<>();
        for (Object element : UiHost.getInstance().findUiElements("tag", "a")) {
            actual.add((WebElement) element);
        }
        confirm(WebElementsCalibrator.construct(expected, actual).validate());
    }

    @Test(dependsOnMethods = "construct")
    public void selectWindow() {
        UiHost host = UiHost.getInstance();
        host.load("http://espn.go.com");
        UiElement.getInstance("'Login' link", UiElement.LocatorType.TAG, "a", UiElement.getInstance("'User' region",
                UiElement.LocatorType.CLASS, "user")).click();
        host.selectFrame("disneyid-iframe");
        host.selectWindow();
        String actual = UiElement.getInstance("'Login' button", UiElement.LocatorType.CLASS, "btn-group").getText();
        confirm(StringCalibrator.construct("Text of element in specific window", null, actual).validate());
    }

    @Test(enabled = false, dataProvider = "drivers")
    public void getSpecifWebDriverInstance(DriverInstantiation driverInstantiation, String expected) {
        String actual = UiHost.getInstance(driverInstantiation).getDriverName();
        confirm(StringCalibrator.construct("UI Host driver name", expected, actual).validate());
    }

    private List<WebElementExpected> composeExpectedWebElements() {
        List<WebElementExpected> expected = new ArrayList<>();
        expected.add(composeExpectedAnchor("https://github.com/tourdedave/the-internet", null));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "abtest"), "A/B Testing"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "basic_auth"), "Basic Auth"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "broken_images"), "Broken Images"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "challenging_dom"), "Challenging DOM"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "checkboxes"), "Checkboxes"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "context_menu"), "Context Menu"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "disappearing_elements"), "Disappearing " +
                "Elements"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "drag_and_drop"), "Drag and Drop"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "dropdown"), "Dropdown"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "dynamic_content"), "Dynamic Content"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "dynamic_controls"), "Dynamic Controls"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "dynamic_loading"), "Dynamic Loading"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "exit_intent"), "Exit Intent"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "download"), "File Download"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "upload"), "File Upload"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "floating_menu"), "Floating Menu"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "forgot_password"), "Forgot Password"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "login"), "Form Authentication"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "frames"), "Frames"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "geolocation"), "Geolocation"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "horizontal_slider"), "Horizontal Slider"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "hovers"), "Hovers"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "infinite_scroll"), "Infinite Scroll"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "jqueryui/menu"), "JQuery UI Menus"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "javascript_alerts"), "JavaScript Alerts"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "javascript_error"), "JavaScript onload event " +
                "" + "error"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "key_presses"), "Key Presses"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "large"), "Large & Deep DOM"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "windows"), "Multiple Windows"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "nested_frames"), "Nested Frames"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "notification_message"), "Notification " +
                "Messages"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "redirector"), "Redirect Link"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "download_secure"), "Secure File Download"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "shifting_content"), "Shifting Content"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "slow"), "Slow Resources"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "tables"), "Sortable Data Tables"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "status_codes"), "Status Codes"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "typos"), "Typos"));
        expected.add(composeExpectedAnchor(String.format("%s/%s", uri, "tinymce"), "WYSIWYG Editor"));
        expected.add(composeExpectedAnchor("http://elementalselenium.com/", "Elemental Selenium"));
        return expected;
    }

    private WebElementExpected composeExpectedAnchor(String href, String text) {
        return WebElementExpected.construct(ANCHOR, null, null, href, text);
    }
}
