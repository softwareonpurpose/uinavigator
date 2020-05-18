package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentInstantiatedTests extends TestClass {
    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Body";
        final String parentLocatorValue = "body";
        final WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Username";
        final String locatorValue = "name";
        final int ordinal = 1;
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.NAME, locatorValue, ordinal, getParent, host);
        //noinspection rawtypes
        Class expected = RemoteWebElement.class;
        getElement.execute();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
