package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        Integer expected = 1;
        Integer actual = WebGetListByLocatorOrdinal.getInstance(new By.ByTagName("option"), 3).execute().size();
        Assert.assertEquals(actual, expected, "Failed to return list of one instance of WebUiElement");
    }
}
