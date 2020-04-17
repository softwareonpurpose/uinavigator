package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiHostJavascriptRelatedExceptionTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
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
}
