package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorParentExecuteSingleElementTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_parentNull() {
        MockView.directNav();
        Class<UiElement> expected = UiElement.class;
        final WebUiGetElementListByLocatorParent getListBehavior =
                WebUiGetElementListByLocatorParent.getInstance(UiLocatorType.TAG, "body", null);
        Class actual = getListBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElements");
    }
}
