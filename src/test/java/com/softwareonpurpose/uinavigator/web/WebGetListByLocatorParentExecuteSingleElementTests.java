package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorParentExecuteSingleElementTests extends TestClass {
    @Test
    public void testExecute_parentNull() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        Class<UiElement> expected = UiElement.class;
        final WebGetElementListByLocatorParent getListBehavior =
                WebGetElementListByLocatorParent.getInstance(UiLocatorType.TAG, "body", null, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getListBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElements");
    }
}
