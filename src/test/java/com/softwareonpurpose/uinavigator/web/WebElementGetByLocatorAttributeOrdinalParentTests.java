package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockViewFramed;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeOrdinalParentTests extends TestClass {
    @Test
    public void testExecute_parentIFrame() {
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        UiHost host = UiHost.getInstance();
        MockViewFramed.directNav(host);
        String parentDescription = "IFrame";
        final String parentLocatorValue = "iframe";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Title Link";
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "a";
        final String attribute = "rel";
        final String attributeValue = "home";
        int ordinal = 1;
        final WebElementGetByLocatorAttributeOrdinalParent getElement =
                WebElementGetByLocatorAttributeOrdinalParent
                        .getInstance(
                                description, locatorType, locatorValue,
                                attribute, attributeValue, ordinal, getParent, host);
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'iframe' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
