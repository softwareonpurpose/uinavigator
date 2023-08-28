package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @SuppressWarnings("rawtypes")
    public void testExecute() {
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebUiGetElement getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final WebUiGetElementListByLocatorAttributeParent getBehavior =
                WebUiGetElementListByLocatorAttributeParent
                        .getInstance(UiLocatorType.TAG, "select", attribute, attributeValue, getParent);
        Class expected = UiElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
