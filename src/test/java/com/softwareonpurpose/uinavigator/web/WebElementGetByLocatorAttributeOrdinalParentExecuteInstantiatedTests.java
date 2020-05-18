package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeOrdinalParentExecuteInstantiatedTests extends TestClass {
    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Select";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final String locatorValue = "select";
        final WebElementGetByLocatorAttributeOrdinalParent getBehavior =
                WebElementGetByLocatorAttributeOrdinalParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        getBehavior.execute();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }
}
