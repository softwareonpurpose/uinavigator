package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentExecuteInstantiatedTests extends TestClass {
    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Username";
        final String parentLocatorValue = "user_name";
        final WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.NAME, parentLocatorValue, host);
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, host);
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
        getElement.execute();
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
