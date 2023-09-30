package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    private static final TestResources resources = TestResources.getInstance();
    private static final UiElement4 byIdNonexistent =
            UiElement4.getInstance("id nonexistent", UiLocatorType4.ID, "nonexistent");
    private static final UiElement4 byIdView =
            UiElement4.getInstance("id existent", UiLocatorType4.ID, "view");
    private static final UiElement4 byIdHeading =
            UiElement4.getInstance("id on sub-element", UiLocatorType4.ID, "heading-id");
    private static final UiElement4 byIdP =
            UiElement4.getInstance("id on descendent", UiLocatorType4.ID, "p-id");
    private static final UiElement4 byTagNonexistent =
            UiElement4.getInstance("tag nonexistent", UiLocatorType4.TAG, "nonexistent");
    private static final UiElement4 byTagBody =
            UiElement4.getInstance("tag 'body'", UiLocatorType4.TAG, "body");
    private static final UiElement4 byTagP =
            UiElement4.getInstance("tag 'p'", UiLocatorType4.TAG, "p");
    private static final UiElement4 byTagLi =
            UiElement4.getInstance("tag 'li'", UiLocatorType4.TAG, "li");
    private static final UiElement4 byTagA =
            UiElement4.getInstance("'Anchor' tag", UiLocatorType4.TAG, "a");
    private static final UiElement4 byTagImg =
            UiElement4.getInstance("'Image' element", UiLocatorType4.TAG, "img");
    private static final UiElement4 byClassNonexistent =
            UiElement4.getInstance("class nonexistent", UiLocatorType4.CLASS, "nonexistent");
    private static final UiElement4 byClassRootElement =
            UiElement4.getInstance("class 'root-element'", UiLocatorType4.CLASS, "root-element");
    private static final UiElement4 byClassError =
            UiElement4.getInstance("class 'error'", UiLocatorType4.CLASS, "error");
    private static final UiElement4 byClassNames =
            UiElement4.getInstance("class 'names'", UiLocatorType4.CLASS, "names");
    private static final String basicPage = "basic";
    private static final String paragraphsPage = "paragraphs";
    private static final String linkPage = "link";
    private static final String listPage = "list";
    private static final String imagePage = "image";
    private static final String headPage = "head";
    private static final String breakPage = "paragraph-break";
    private static final String prePage = "pre";
    private static final String stylePage = "style";
    private static final String idPage = "id";
    private static final String classPage = "class";
    private static final String tablesPage = "tables";

    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        boolean isDisplayed = true;
        boolean isNotDisplayed = false;
        return new Object[][]
                {
                        {idPage, byIdNonexistent, isNotDisplayed}
                        , {idPage, byIdView, isDisplayed}
                        , {idPage, byIdHeading, isDisplayed}
                        , {idPage, byIdP, isDisplayed}
                        , {basicPage, byTagNonexistent, isNotDisplayed}
                        , {basicPage, byTagBody, isDisplayed}
                        , {basicPage, byTagP, isDisplayed}
                        , {listPage, byTagLi, isDisplayed}
                        , {basicPage, byClassNonexistent, isNotDisplayed}
                        , {classPage, byClassRootElement, isDisplayed}
                        , {classPage, byClassError, isDisplayed}
                        , {tablesPage, byClassNames, isDisplayed}
                };
    }

    @DataProvider
    public static Object[][] scenarios_getText() {
        String basicPageParagraph = "My first paragraph.";
        String basicPageText = String.format("%s\n%s", "My First Heading", basicPageParagraph);
        String listPageListItem1 = "Coffee (unordered)";
        return new Object[][]{
                {idPage, byIdNonexistent, null}
                , {basicPage, byTagBody, basicPageText}
                , {basicPage, byTagP, basicPageParagraph}
                , {listPage, byTagLi, listPageListItem1}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getHref() {
        return new Object[][]{
                {linkPage, byTagP, null}
                , {basicPage, byTagA, null}
                , {linkPage, byTagA, "https://www.w3schools.com/"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_click() {
        return new Object[][]{
                {basicPage, byTagP, "basic"}
                , {basicPage, byTagA, "basic"}
                , {linkPage, byTagA, "https://www.w3schools.com/"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getAttribute() {
        return new Object[][]{
                {imagePage, byTagNonexistent, "src", null}
                , {imagePage, byTagImg, "bogus", null}
                , {imagePage, byTagImg, "src", "https://www.w3schools.com/html/w3schools.jpg"}
                , {imagePage, byTagImg, "alt", "W3Schools.com"}
                , {imagePage, byTagImg, "width", "104"}
                , {stylePage, byTagP, "style", "color: red;"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getStyleProperty() {
        String color = "color";
        String bogus = "bogus";
        String red = "rgba(255, 0, 0, 1)";
        return new Object[][]{
                {byTagA, color, null}
                , {byTagP, bogus, ""}
                , {byTagP, color, red}
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
    @Test(enabled = false)
    public void getInstance_ordinal() {
        Class expected = UiElement4.class;
        String description = "'paragraph' element";
        String locatorType = UiLocatorType4.TAG;
        String locatorValue = "p";
        int ordinal = 2;
        Class actual = UiElement4.getInstance(description, locatorType, locatorValue, ordinal).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test(enabled = false)
    public void getInstance_parent() {
        Class expected = UiElement4.class;
        UiElement4 parent = UiElement4.getInstance("'Unordered List' element", UiLocatorType4.TAG, "ul");
        Class actual = UiElement4.getInstance("'List Item' element", UiLocatorType4.TAG, "li", parent).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test(enabled = false)
    public void getInstance_ordinal_parent() {
        Class expected = UiElement4.class;
        UiElement4 parent = UiElement4.getInstance("'Ordered List' element", UiLocatorType4.TAG, "ol");
        Class actual = UiElement4.getInstance("'List Item' element", UiLocatorType4.TAG, "li", 2, parent).getClass();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_isDisplayed")
    public void isDisplayed(String page, UiElement4 element, boolean expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getText")
    public void getText(String page, UiElement4 element, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        String actual = element.getText();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getHref")
    public void getHref(String page, UiElement4 element, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        String actual = element.getHref();
        Assert.assertEquals(actual, expected);
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

    @Test(dataProvider = "scenarios_getAttribute")
    public void getAttribute(String page, UiElement4 element, String attribute, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        String actual = element.getAttribute(attribute);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getStyleProperty")
    public void getStyleProperty(UiElement4 element, String styleProperty, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(stylePage));
        String actual = element.getStyleProperty(styleProperty);
        Assert.assertEquals(actual, expected);
    }
}