package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiHost4Tests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_load_result() {
        return new Object[][]{
                {"https://www.w3schools.com/html/tryit.asp?filename=tryhtml_basic_document", true}
                , {resources.getPageUrl("nonexistent"), false}
        };
    }

    @DataProvider
    public static Object[][] scenarios_currentUrl() {
        String url_localHtml = resources.getPageUrl("redirected");
        String url_localHtmlExpected = url_localHtml.replace("file:/", "file:///");
        String url_withQueryString = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_basic_document";
        String url_withQueryStringExpected = "https://www.w3schools.com/html/tryit.asp";
        return new Object[][]{
                {url_withQueryString, url_withQueryStringExpected, true}
                , {url_localHtml, url_localHtmlExpected, false}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_load_result")
    public void load_result(String url, Boolean expected) {
        UiHost4 host = UiHost4.getInstance();
        Boolean actual = host.load(url);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_currentUrl")
    public void load_currentUrl(String url, String urlDestination, Boolean expected) {
        UiHost4 host = UiHost4.getInstance();
        host.load(url);
        String currentUrl = host.getCurrentUrl();
        Boolean actual = currentUrl.contains(urlDestination);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getTitle() {
        String expected = "My First HTML";
        UiHost4 browser = UiHost4.getInstance();
        browser.load(TestResources.getInstance().getPageUrl("head"));
        String actual = browser.getTitle();
        Assert.assertEquals(actual, expected);
    }
}
