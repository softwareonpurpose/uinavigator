package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagLocatorNullParent() {
        Class expected = WebGetElementByLocatorParent.class;
        Class actual = WebGetElementByLocatorParent.getInstance(new By.ByTagName("body"), null).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagLocatorNullParent() {
        Class expected = WebGetElementByLocatorParent.class;
        Class actual = WebGetElementByLocatorParent.getInstance(new By.ByTagName("select"), null).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_nameLocatorNullParent() {
        Class expected = WebGetElementByLocatorParent.class;
        Class actual = WebGetElementByLocatorParent.getInstance(new By.ByName("user_name"), null).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        final By.ByName locator = new By.ByName("user_name");
        Class expected = RemoteWebElement.class;
        Class actual = WebGetElementByLocatorParent.getInstance(locator, null).execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }
}
