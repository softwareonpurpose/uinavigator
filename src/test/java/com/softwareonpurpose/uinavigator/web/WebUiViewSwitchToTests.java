package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockViewFramed;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebUiViewSwitchToTests extends TestClass {
    @Test
    public void testSwitchTo_iFrame() {
        host = UiHost.getInstance();
        final MockViewFramed view = MockViewFramed.directNav(host);
        String expected = "Life Reconciled";
        String actual = view.getSiteTitle();
        Assert.assertEquals(actual, expected, "Failed to successfully switch to iframe");
    }
}
