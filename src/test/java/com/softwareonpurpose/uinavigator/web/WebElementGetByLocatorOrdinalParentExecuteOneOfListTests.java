package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentExecuteOneOfListTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String description = "Select";
        final String parentLocatorValue = "select";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, parentLocatorValue, host);
        final String locatorValue = "option";
        final int ordinal = 4;
        final WebElementGetByLocatorOrdinalParent getBehavior =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of RemoteWebElement";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
