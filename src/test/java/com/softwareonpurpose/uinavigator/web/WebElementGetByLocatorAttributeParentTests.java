package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeParentTests extends TestClass {
    @Test
    public void testExecute_parentInstance() {
        UiHost host = UiHost.getInstance();
        String parentDescription = "Form";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        WebElementGet getParent = WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, "form", host);
        String description = "Name";
        final String locatorValue = "name";
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of WebElement";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
