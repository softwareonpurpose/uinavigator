package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collection;

@Test
public class WebGetListByLocatorParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_parentNull() {
        MockView.directNav();
        Class expected = WebUiElement.class;
        final Collection<WebUiElement> getListBehavior =
                WebGetListByLocatorParent.getInstance(new By.ByTagName("body"), null).execute();
        Class actual = getListBehavior.iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElements");
    }

    @Test
    public void testExecute_multipleElements() {
        MockView.directNav();
        Class expected = WebUiElement.class;
        final Collection<WebUiElement> getListBehavior =
                WebGetListByLocatorParent.getInstance(new By.ByTagName("select"), null).execute();
        Class actual = getListBehavior.iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElements");
    }
}
