package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String description = "Option";
        final String locatorValue = "option";
        final int ordinal = 5;
        final WebElementGet getElement =
                WebElementGetByLocatorOrdinal.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement");
    }
}
