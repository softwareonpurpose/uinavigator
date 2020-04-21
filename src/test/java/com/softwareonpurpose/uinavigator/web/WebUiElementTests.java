package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetText() {
        String expected = "google";
        MockView.directNav();
        String actual = WebUiElement.getInstance("Element", new By.ByTagName("a")).getText();
        Assert.assertEquals(actual, expected, "Failed to return text value of WebUiElement");
    }

    @Test
    public void testGetList() {
        int expected = 4;
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(new By.ByTagName("body"));
        MockView.directNav();
        int actual = WebUiElement.getList("Element List", new By.ByTagName("select"), getParent).size();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }

    @Test
    public void testGetList_nonExistentParent() {
        int expected = 0;
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(new By.ByTagName("bogus"));
        MockView.directNav();
        int actual = WebUiElement.getList("Element List", new By.ByTagName("select"), getParent).size();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
