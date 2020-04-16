package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.mock.BrokenMockView;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiViewTests {
    @Test
    public void testNonPublicConstructor() {
        Class expected = NullPointerException.class;
        Class actual = null;
        try {
            BrokenMockView.directNav();
        } catch (NullPointerException e) {
            actual = e.getClass();
        }
        final String message =
                "Failed to throw Null Pointer Exception when View constructor is non-public";
        Assert.assertEquals(actual, expected, message);
    }
}
