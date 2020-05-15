package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorParentConstructorTests extends TestClass {
    @Test
    public void testConstructor_optionTagParentNull() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        Class<WebGetElementListByLocatorParent> expected = WebGetElementListByLocatorParent.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocatorParent.getInstance(UiLocatorType.TAG, "option", null, UiDriverGet.getInstance()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebGetListByLocatorParent");
    }
}
