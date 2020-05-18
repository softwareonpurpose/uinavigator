package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentExecuteInstantiatedTests extends TestClass {
    @Test
    public void testExecute_instantiated() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        String description = "Username";
        final String locatorValue = "user_name";
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent, host);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        getElement.execute();
        //noinspection rawtypes
        Class actual = getElement.execute().getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
