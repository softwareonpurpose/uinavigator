package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import com.softwareonpurpose.uinavigator.web.WebUiView;
import com.softwareonpurpose.uinavigator.web.mock.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiViewTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

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

    @Test(groups="debug")
    public void testConstructor() {
        MockView.directNav();
        Class expected = UiView.class;
        Class actual = WebUiView.expect(MockView.class).getClass().getSuperclass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of UiView");
    }

    @Test
    public void testConstructor_confirmationFailure() {
        UnstableMockView.directNav();
        Class expected = UiView.class;
        Class actual = WebUiView.expect(UnstableMockView.class).getClass().getSuperclass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of UiView");
    }

    @Test
    public void testLoad_queryString() {
        QueryStringMockView.directNav();
        Class expected = UiView.class;
        Class actual = WebUiView.expect(QueryStringMockView.class).getClass().getSuperclass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of UiView");
    }

    @Test
    public void testLoad_relativePath() {
        RelativePathMockView.directNav();
        Class expected = UiView.class;
        Class actual = WebUiView.expect(RelativePathMockView.class).getClass().getSuperclass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of UiView");
    }
}
