package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetAttributeBehaviorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        WebGetElementBehavior getBehavior = WebGetElementByLocator.getInstance(new By.ById("empty-select-two"));
        String expected = "bogus";
        MockView.directNav();
        String actual = WebGetAttributeBehavior.getInstance(getBehavior).execute("data-test");
        Assert.assertEquals(actual, expected, "Failed to return attribute value");
    }

    @Test
    public void testExecute_nullArgument() {
        WebGetElementBehavior getBehavior = WebGetElementByLocator.getInstance(new By.ById("empty-select-two"));
        MockView.directNav();
        String actual = WebGetAttributeBehavior.getInstance(getBehavior).execute(null);
        Assert.assertNull(actual, "Failed to return null when requested attribute is null");
    }
}
