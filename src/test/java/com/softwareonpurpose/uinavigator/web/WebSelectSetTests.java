package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebSelectSetTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String description = "Select";
        final String locatorValue = "pet-select";
        WebElementGet getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        UiElementGetText getText = WebSelectGetText.getSelectInstance(getElement);
        UiElementSet setSelect = WebSelectSet.getSelectInstance(getElement);
        MockView.directNav(host);
        String expected = "Dog";
        setSelect.execute(expected);
        String actual = getText.execute();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to set 'select' element to expected value");
    }
}
