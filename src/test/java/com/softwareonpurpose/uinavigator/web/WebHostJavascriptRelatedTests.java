package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebHostJavascriptRelatedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String javascript = "document.getElementsByName('user_name')[0].value = 'whatever';";
        String expected = "whatever";
        MockView.directNav();
        final WebHost host = WebHost.getInstance();
        host.execute(javascript);
        final WebElement element = host.findUiElement(new By.ByName("user_name"));
        final String actual = element.getAttribute("value");
        Assert.assertEquals(actual, expected, "Failed to execute javascript successfully");
    }

    @Test
    public void testSetAttribute() {
        final String elementName = "user_name";
        final String attribute = "data-test";
        String expected = "updated";
        MockView.directNav();
        final WebHost host = WebHost.getInstance();
        final WebElement element = host.findUiElement(new By.ByName(elementName));
        host.setAttribute(element, attribute, expected);
        String actual = host.findUiElement(new By.ByName(elementName)).getAttribute(attribute);
        Assert.assertEquals(actual, expected, "Failed to update value of attribute");
    }
}
