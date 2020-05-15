package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetHrefTests extends TestClass {
    @Test
    public void testGetHref() {
        host = UiHost.getInstance();
        String expected = "http://www.google.com/";
        MockView.directNav(host);
        String actual = UiElement.getInstance("'Link' element", UiLocatorType.TAG, "a").getHref();
        Assert.assertEquals(actual, expected, "Failed to return HREF of element");
    }
}
