package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeOrdinalParentNullParentTests extends TestClass {
    @Test
    public void testExecute_parentNull() {
        UiHost host = UiHost.getInstance();
        String description = "View";
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final int ordinal = 1;
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        final String locatorValue = "body";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorAttributeOrdinalParent getBehavior =
                WebElementGetByLocatorAttributeOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue,
                                attribute, attributeValue, ordinal, getParent, host);
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }
}
