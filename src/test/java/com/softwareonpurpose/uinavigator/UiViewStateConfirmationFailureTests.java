package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiViewStateConfirmationFailureTests {
    @AfterMethod
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testConfirmElementStates_failure() {
        final MockViewConfirmationFailure actual = MockViewConfirmationFailure.directNav();
        final String message = "Failed to return an instance of UiView when state confirmation fails";
        Assert.assertNotNull(actual, message);
    }
}
