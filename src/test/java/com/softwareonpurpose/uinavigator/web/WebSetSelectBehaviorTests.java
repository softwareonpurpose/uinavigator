package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetTextBehavior;
import com.softwareonpurpose.uinavigator.SetElementBehavior;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebSetSelectBehaviorTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        WebGetElementBehavior getElement = WebGetElementByLocator.getInstance(UiLocatorType.ID, "pet-select");
        GetTextBehavior getText = WebGetTextSelectBehavior.getSelectInstance(getElement);
        SetElementBehavior setSelect = WebSetSelectBehavior.getSelectInstance(getElement);
        MockView.directNav();
        String expected = "Dog";
        setSelect.execute(expected);
        String actual = getText.execute();
        Assert.assertEquals(actual, expected, "Failed to set 'select' element to expected value");
    }
}
