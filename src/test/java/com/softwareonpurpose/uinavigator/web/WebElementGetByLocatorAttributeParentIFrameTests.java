package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockViewFramed;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeParentIFrameTests extends TestClass {
    @Test
    public void testExecute_parentIFrame() {
        UiHost host = UiHost.getInstance();
        MockViewFramed.directNav(host);
        String parentDescription = "IFrame";
        final String parentLocatorValue = "iframe";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Title";
        final UiLocatorType locatorType = UiLocatorType.TAG;
        final String locatorValue = "a";
        final String attributeValue = "home";
        final String attribute = "rel";
        final WebElementGetByLocatorAttributeParent getElement =
                WebElementGetByLocatorAttributeParent
                        .getInstance(
                                description, locatorType, locatorValue, attribute, attributeValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'iframe' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
