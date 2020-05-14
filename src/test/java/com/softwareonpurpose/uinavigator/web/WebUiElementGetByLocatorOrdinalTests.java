package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetByLocatorOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String description = "Option";
        final String locatorValue = "option";
        final int ordinal = 5;
        final WebUiElementGet getElement =
                WebUiElementGetByLocatorOrdinal.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement");
    }
}
