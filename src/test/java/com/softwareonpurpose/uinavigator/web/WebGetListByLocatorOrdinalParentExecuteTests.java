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
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String parentDescription = "Select";
        MockView.directNav();
        final String locatorValue = "select";
        WebUiGetElement getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue);
        final int expected = 1;
        final int ordinal = 3;
        final WebUiGetElementListByLocatorOrdinalParent getListBehavior =
                WebUiGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "option", ordinal, getParent);
        Collection<UiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }

    @Test
    public void testExecute_parentNull() {
        MockView.directNav();
        final int expected = 1;
        final int ordinal = 1;
        final WebUiGetElement getParent = null;
        //noinspection ConstantConditions
        final WebUiGetElementListByLocatorOrdinalParent getListBehavior =
                WebUiGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", ordinal, getParent);
        Collection<UiElement> actual = getListBehavior.execute();
        Assert.assertEquals(actual.size(), expected, "Failed to return one element in a list");
    }
}
