package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collection;

@Test
public class WebGetListByLocatorParentExecuteTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String parentDescription = "Select";
        MockView.directNav();
        final String parentLocatorValue = "select";
        WebUiGetElement getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        Class<WebUiElement> expected = WebUiElement.class;
        final Collection<WebUiElement> getListBehavior =
                WebUiGetElementListByLocatorParent.getInstance(UiLocatorType.TAG, "option", getParent).execute();
        //noinspection rawtypes
        Class actual = getListBehavior.iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElements");
    }
}
