package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SuppressWarnings("ConstantConditions")
@Test
public class UiElement4Tests {
    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        String body = "body";
        String heading_1 = "h1";
        String paragraph = "p";
        Integer second = 2;
        boolean isDisplayed = true;
        boolean isNotDisplayed = false;
        return new Object[][]
                {
                        {"basic", UiElement4.getInstance("'body' element'", UiLocatorType4.TAG, body), isDisplayed}
                        , {"basic", UiElement4.getInstance("'heading' element'", UiLocatorType4.TAG, heading_1), isDisplayed}
                        , {"basic", UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, paragraph, second), isNotDisplayed}
                        , {"paragraphs", UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, paragraph, second), isDisplayed}
                };
    }

    @DataProvider
    public static Object[][] scenarios_getText() {
        String basicPage = "basic";
        String basicLink = "link";
        String firstHeading = "My First Heading";
        String firstParagraph = "My first paragraph.";
        String fullBody = String.format("%s\n%s", firstHeading, firstParagraph);
        return new Object[][]{
                {basicPage, "h1", firstHeading}
                , {basicPage, "p", firstParagraph}
                , {basicPage, "body", fullBody}
                , {basicPage, "div", null}
                , {basicLink, "a", "This is a link"}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance() {
        Class expected = UiElement4.class;
        String description = "'body' element";
        String locatorType = UiLocatorType4.TAG;
        String locatorValue = "body";
        Class actual = UiElement4.getInstance(description, locatorType, locatorValue).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_ordinal() {
        Class expected = UiElement4.class;
        String description = "'paragraph' element";
        String locatorType = UiLocatorType4.TAG;
        String locatorValue = "p";
        Integer ordinal = 2;
        Class actual = UiElement4.getInstance(description, locatorType, locatorValue, ordinal).getClass();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_isDisplayed")
    public void isDisplayed(String page, UiElement4 element, boolean expected) {
        UiHost4.getInstance().load(getPageUrl(page));
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }

    private String getPageUrl(String page) {
        return getClass().getResource(String.format("/%s.html", page)).toString();
    }

    @Test(dataProvider = "scenarios_getText")
    public void getText(String page, String tag, String expected) {
        UiHost4.getInstance().load(getPageUrl(page));
        String actual = UiElement4.getInstance("'" + tag + "' tag", UiLocatorType4.TAG, tag).getText();
        Assert.assertEquals(actual, expected);
    }
}
