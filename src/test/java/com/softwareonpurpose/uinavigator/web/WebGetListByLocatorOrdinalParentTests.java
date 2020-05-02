package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
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
        Class<WebGetListByLocatorOrdinalParent> expected = WebGetListByLocatorOrdinalParent.class;
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagNullParent() {
        Class<WebGetListByLocatorOrdinalParent> expected = WebGetListByLocatorOrdinalParent.class;
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "select", 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTagPrentInstance() {
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "select");
        Class<WebGetListByLocatorOrdinalParent> expected = WebGetListByLocatorOrdinalParent.class;
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "select", 1, getParent).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "select");
        final int expected = 1;
        final WebGetListByLocatorOrdinalParent getListBehavior =
                WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "option", 3, getParent);
        Collection<UiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }

    @Test
    public void testExecute_parentNull() {
        MockView.directNav();
        final int expected = 1;
        final WebGetListByLocatorOrdinalParent getListBehavior =
                WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", 1, null);
        Collection<UiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }
}
