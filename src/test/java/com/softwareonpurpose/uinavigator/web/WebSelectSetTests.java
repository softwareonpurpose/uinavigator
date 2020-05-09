package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ElementGetText;
import com.softwareonpurpose.uinavigator.ElementSet;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebSelectSetTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String description="Select";
        final String locatorValue = "pet-select";
        WebUiGetElement getElement =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        ElementGetText getText = WebSelectGetText.getSelectInstance(getElement);
        ElementSet setSelect = WebSelectSet.getSelectInstance(getElement);
        MockView.directNav();
        String expected = "Dog";
        setSelect.execute(expected);
        String actual = getText.execute();
        Assert.assertEquals(actual, expected, "Failed to set 'select' element to expected value");
    }
}
