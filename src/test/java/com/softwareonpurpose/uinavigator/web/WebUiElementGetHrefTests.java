package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetHrefTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetHref() {
        String expected = "http://www.google.com/";
        MockView.directNav();
        String actual = WebUiElement.getInstance("'Link' element", new By.ByTagName("a")).getHref();
        Assert.assertEquals(actual, expected, "Failed to return HREF of element");
    }
}
