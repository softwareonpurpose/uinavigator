package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorParentConstructorTests extends TestClass {
    @Test
    public void testConstructor_optionTagParentNull() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        Class<WebGetElementListByLocatorParent> expected = WebGetElementListByLocatorParent.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocatorParent.getInstance(UiLocatorType.TAG, "option", null, host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebGetListByLocatorParent");
    }
}
