package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

@Test
public class WebGetListByLocatorOrdinalParentExecuteTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String parentDescription = "Select";
        MockView.directNav(host);
        final String locatorValue = "select";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue, host);
        final int expected = 1;
        final int ordinal = 3;
        final WebGetElementListByLocatorOrdinalParent getListBehavior =
                WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "option", ordinal, getParent, host);
        Collection<UiElement> actual = getListBehavior.execute();
        host.quit();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }

    @Test
    public void testExecute_parentNull() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        final int expected = 1;
        final int ordinal = 1;
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebGetElementListByLocatorOrdinalParent getListBehavior =
                WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", ordinal, getParent, host);
        Collection<UiElement> actual = getListBehavior.execute();
        host.quit();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }
}
