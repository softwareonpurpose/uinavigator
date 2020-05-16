package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String description = "Option";
        final String locatorValue = "option";
        final int ordinal = 5;
        final WebElementGet getElement =
                WebElementGetByLocatorOrdinal.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement");
    }
}
