package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collection;

@Test
public class WebGetListByLocatorOrdinalParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagNullParent() {
        Class expected = WebGetListByLocatorOrdinalParent.class;
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(new By.ByTagName("body"), 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagNullParent() {
        Class expected = WebGetListByLocatorOrdinalParent.class;
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(new By.ByTagName("select"), 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTagPrentInstance() {
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(new By.ByTagName("select"));
        Class expected = WebGetListByLocatorOrdinalParent.class;
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(new By.ByTagName("select"), 1, getParent).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(new By.ByTagName("select"));
        final int expected = 1;
        final WebGetListByLocatorOrdinalParent getListBehavior =
                WebGetListByLocatorOrdinalParent.getInstance(new By.ByTagName("option"), 3, getParent);
        Collection<WebUiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }

    @Test
    public void testExecute_parentNull() {
        MockView.directNav();
        final int expected = 1;
        final WebGetListByLocatorOrdinalParent getListBehavior =
                WebGetListByLocatorOrdinalParent.getInstance(new By.ByTagName("body"), 1, null);
        Collection<WebUiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }
}
