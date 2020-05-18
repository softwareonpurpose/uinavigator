package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorTests extends TestClass {
    @Test
    public void testExecute_iFrame() {
        UiHost host = UiHost.getInstance();
        String description = "IFrame";
        MockView.directNav(host);
        final String locatorValue = "iframe";
        WebElement actual =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, host).execute();
        host.quit();
        Assert.assertNotNull(actual, "Failed to return instance of 'iframe' WebElement");
    }
}
