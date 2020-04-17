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

    @Test
    public void testExecute_nonExistentOrdinal() {
        MockView.directNav();
        final int expected = 0;
        final WebGetListByLocatorOrdinalParent getListBehavior =
                WebGetListByLocatorOrdinalParent.getInstance(new By.ByTagName("body"), 5, null);
        Collection<WebUiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return an empty list for nonexistent ordinal");
    }
}
