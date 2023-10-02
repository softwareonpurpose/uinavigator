package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4ActionTests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_click() {
        final UiElement4 byTagPNotClickable =
                UiElement4.getInstance("tag 'p'", UiLocatorType4.TAG, "p");
        final UiElement4 byTagA =
                UiElement4.getInstance("'Anchor' tag", UiLocatorType4.TAG, "a");
        final UiElement4 byTagAOrdinal2 =
                UiElement4.getInstance("'Anchor' tag #2", UiLocatorType4.TAG, "a", 2);
        final String basicPage = "basic";
        final String linkPage = "link";
        return new Object[][]{
                {basicPage, byTagPNotClickable, "basic"}
                , {basicPage, byTagA, "basic"}
                , {linkPage, byTagA, "https://www.google.com/"}
                , {linkPage, byTagAOrdinal2, "https://www.youtube.com/"}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_click")
    public void click(String page, UiElement4 element, String expected) {
        expected = expected.contains("http")
                ? expected
                : resources.getPageUrl(expected).replace("file:/", "file:///");
        UiHost4.getInstance().load(resources.getPageUrl(page));
        element.click();
        String actual = UiHost4.getInstance().getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }
}