package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOnlyTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_typeClass() {
        Class expected = WebGetListByLocatorOnly.class;
        Class actual = WebGetListByLocatorOnly.getInstance(new By.ByClassName("any")).getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocatorOnly");
    }

    @Test
    public void testConstructor_typeId() {
        Class expected = WebGetListByLocatorOnly.class;
        Class actual = WebGetListByLocatorOnly.getInstance(new By.ById("any")).getClass();
        Assert.assertEquals(actual, expected, "Failed to get an instance of WebGetListByLocatorOnly");
    }

    @Test
    public void testExecute_nonexistent() {
        int expected = 0;
        int actual = WebGetListByLocatorOnly.getInstance(new By.ById("nonexistent")).execute().size();
        Assert.assertEquals(actual, expected, "Failed to return an empty Collection");
    }

    @Test
    public void testExecute_confirmType() {
        Class expected = WebUiElement.class;
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        WebUiHost.getInstance().load(uri);
        WebGetListByLocatorOnly listBehavior = WebGetListByLocatorOnly.getInstance(new By.ByTagName("option"));
        Class actual = listBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a Collection of WebUiElement");
    }
}
