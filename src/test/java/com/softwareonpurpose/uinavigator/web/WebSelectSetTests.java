package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElementGetText;
import com.softwareonpurpose.uinavigator.UiElementSet;
import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebSelectSetTests {
    @AfterMethod
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute() {
        String description="Select";
        final String locatorValue = "pet-select";
        WebElementGet getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        UiElementGetText getText = WebSelectGetText.getSelectInstance(getElement);
        UiElementSet setSelect = WebSelectSet.getSelectInstance(getElement);
        MockView.directNav();
        String expected = "Dog";
        setSelect.execute(expected);
        String actual = getText.execute();
        Assert.assertEquals(actual, expected, "Failed to set 'select' element to expected value");
    }
}
