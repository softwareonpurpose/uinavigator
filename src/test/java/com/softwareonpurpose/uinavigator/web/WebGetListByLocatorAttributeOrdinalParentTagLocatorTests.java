package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalParentTagLocatorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_bodyTag() {
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebUiGetElement getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        Class<UiElement> expected = UiElement.class;
        MockView.directNav();
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final int ordinal = 1;
        final String locatorValue = "body";
        final WebUiGetElementListByLocatorAttributeOrdinalParent getBehavior =
                WebUiGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal, getParent);
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
