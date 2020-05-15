package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiViewStateConfirmationFailureTests extends TestClass {
    @Test
    public void testConfirmElementStates_failure() {
        host = UiHost.getInstance();
        final MockViewConfirmationFailure actual = MockViewConfirmationFailure.directNav(host);
        final String message = "Failed to return an instance of UiView when state confirmation fails";
        Assert.assertNotNull(actual, message);
    }
}
