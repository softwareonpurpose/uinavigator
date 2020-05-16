package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

@Test
public class WebGetListByLocatorParentExecuteTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String parentDescription = "Select";
        MockView.directNav(host);
        final String parentLocatorValue = "select";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        Class<UiElement> expected = UiElement.class;
        final Collection<UiElement> getListBehavior =
                WebGetElementListByLocatorParent.getInstance(UiLocatorType.TAG, "option", getParent, host).execute();
        //noinspection rawtypes
        Class actual = getListBehavior.iterator().next().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElements");
    }
}
