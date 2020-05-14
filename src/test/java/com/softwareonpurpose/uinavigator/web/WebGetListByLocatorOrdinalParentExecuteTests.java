package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collection;

@Test
public class WebGetListByLocatorOrdinalParentExecuteTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String parentDescription = "Select";
        MockView.directNav();
        final String locatorValue = "select";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue);
        final int expected = 1;
        final int ordinal = 3;
        final WebGetElementListByLocatorOrdinalParent getListBehavior =
                WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "option", ordinal, getParent);
        Collection<UiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }

    @Test
    public void testExecute_parentNull() {
        MockView.directNav();
        final int expected = 1;
        final int ordinal = 1;
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebGetElementListByLocatorOrdinalParent getListBehavior =
                WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", ordinal, getParent);
        Collection<UiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }
}
