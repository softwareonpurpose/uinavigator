package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeParentNullParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @SuppressWarnings("rawtypes")
    public void testExecute_nullParent() {
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final WebUiGetElementListByLocatorAttributeParent getBehavior =
                WebUiGetElementListByLocatorAttributeParent.getInstance(UiLocatorType.TAG, "body", attribute, attributeValue, null);
        Class expected = UiElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
