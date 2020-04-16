package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import com.softwareonpurpose.uinavigator.web.mock.RelativePathMockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiHostTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testFindUiElement_nonexistent() {
        WebElement expected = null;
        WebElement actual = WebUiHost.getInstance().findUiElement(new By.ById("non-existent"));
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return null for non-existent element");
    }

    @Test
    public void testGetUri() {
        String expected = MockView.directNav().getUri();
        String actual = WebUiHost.getInstance().getUri();
        Assert.assertEquals(actual, expected, "Failed to return expected URI");
    }

    @Test
    public void testGetCookieValue() {
        RelativePathMockView.directNav();
        String actual = WebUiHost.getInstance().getCookieValue("NID", "www.google.com", "/");
        Assert.assertFalse((actual == null || actual.isEmpty()), "Failed to return a cookie value");
    }

    @Test
    public void testGetInstance() {
        Class expected = WebUiHost.class;
        Class actual = WebUiHost.getInstance(DefaultChromeInstantiation.getInstance()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiHost");
    }

    @Test
    public void testExecute() {
        String javascript = "document.getElementsByName('user_name')[0].value = 'whatever';";
        String expected = "whatever";
        MockView.directNav();
        final WebUiHost host = WebUiHost.getInstance();
        host.execute(javascript);
        final WebElement element = host.findUiElement(new By.ByName("user_name"));
        final String actual = element.getAttribute("value");
        Assert.assertEquals(actual, expected, "Failed to execute javascript successfully");
    }

    @Test
    public void testExecute_javascriptExecutorException() {
        String javascript = "document.getElementsByName('nonexistent')[0].value = 'whatever';";
        MockView.directNav();
        final WebUiHost host = WebUiHost.getInstance();
        host.execute(javascript);
        final WebElement element = host.findUiElement(new By.ByName("user_name"));
        final String actual = element.getAttribute("value");
        final String message = "Failed to return an empty String when JavascriptExecutor Exception thrown";
        Assert.assertTrue(actual.isEmpty(), message);
    }

    @Test
    public void testExecute_waitUntilVisible() {
        MockView.directNav();
        final WebUiHost host = WebUiHost.getInstance();
        final WebElement element = host.findUiElement(new By.ByName("nonexistent"));
        final boolean actual = host.waitUntilVisible(element);
        Assert.assertFalse(actual, "Failed to return false for nonexistent element");
    }
}
