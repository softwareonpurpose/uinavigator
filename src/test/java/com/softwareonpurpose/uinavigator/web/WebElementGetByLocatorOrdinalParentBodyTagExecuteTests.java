package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentBodyTagExecuteTests extends TestClass {
    @Test
    public void testExecute_bodyTagParentNull() {
        UiHost host = UiHost.getInstance();
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 1;
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorOrdinalParent getBehavior =
                WebElementGetByLocatorOrdinalParent.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of RemoteWebElement";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
