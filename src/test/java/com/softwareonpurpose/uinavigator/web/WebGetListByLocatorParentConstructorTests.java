package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorParentConstructorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testConstructor_optionTagParentNull() {
        MockView.directNav();
        Class<WebGetElementListByLocatorParent> expected = WebGetElementListByLocatorParent.class;
        Class actual = WebGetElementListByLocatorParent.getInstance(UiLocatorType.TAG, "option", null).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebGetListByLocatorParent");
    }
}
