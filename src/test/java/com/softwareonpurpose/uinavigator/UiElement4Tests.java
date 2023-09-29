package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    private static final TestResources resources = TestResources.getInstance();
    private static final UiElement4 byIdNonexistent = UiElement4.getInstance("id nonexistent", UiLocatorType4.ID, "nonexistent");
    private static final UiElement4 byIdView = UiElement4.getInstance("id existent", UiLocatorType4.ID, "view");
    private static final UiElement4 byIdHeading = UiElement4.getInstance("id on sub-element", UiLocatorType4.ID, "heading-id");
    private static final UiElement4 byIdP = UiElement4.getInstance("id on descendent", UiLocatorType4.ID, "p-id");
    private static final UiElement4 byTagNonexistent = UiElement4.getInstance("tag nonexistent", UiLocatorType4.TAG, "nonexistent");
    private static final UiElement4 byTagBody = UiElement4.getInstance("tag 'body'", UiLocatorType4.TAG, "body");
    private static final UiElement4 byTagP = UiElement4.getInstance("tag 'p'", UiLocatorType4.TAG, "p");
    private static final UiElement4 byTagLi = UiElement4.getInstance("tag 'li'", UiLocatorType4.TAG, "li");




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
                };
    }

    @DataProvider
    public static Object[][] scenarios_getText() {
        //noinspection TextBlockMigration
        String myBonnie =
                "   My Bonnie lies over the ocean.\n\n   My Bonnie lies over the sea.\n\n"
                        + "   My Bonnie lies over the ocean.\n\n   Oh, bring back my Bonnie to me.";
        String firstHeading = "My First Heading";
        String firstParagraph = "My first paragraph.";
        String fullBody = String.format("%s\n%s", firstHeading, firstParagraph);
        return new Object[][]{
//                {basicPage, headingElement, firstHeading}
//                , {basicPage, paragraphElement, firstParagraph}
//                , {basicPage, bodyElement, fullBody}
//                , {basicPage, divElement, null}
//                , {linkPage, anchorElement, "This is a link"}
//                , {listPage, unorderedListItemElement, "Coffee"}
//                , {listPage, nthOrderedListItemElement, "Milk"}
//                , {breakPage, paragraphElement, "This is\na paragraph\nwith line breaks."}
//                , {prePage, preElement, myBonnie}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getHref() {
        return new Object[][]{
                {linkPage, UiElement4.getInstance("'Anchor' tag", UiLocatorType4.TAG, "a"), "https://www.w3schools.com/"}
                , {basicPage, UiElement4.getInstance("'Anchor' tag", UiLocatorType4.TAG, "a"), null}
                , {linkPage, UiElement4.getInstance("'Paragraph' tag", UiLocatorType4.TAG, "p"), null}
        };
    }

    @DataProvider
    public static Object[][] scenarios_click() {
        return new Object[][]{
                {linkPage, UiElement4.getInstance("'This is a link' anchor", UiLocatorType4.TAG, "a"), "https://www.w3schools.com/"}
                , {basicPage, UiElement4.getInstance("'This is a link' anchor", UiLocatorType4.TAG, "a"), "basic"}
                , {basicPage, UiElement4.getInstance("'heading' element'", UiLocatorType4.TAG, "h1"), "basic"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getAttribute() {
        UiElement4 imageElement =
                UiElement4.getInstance("'Image' element", UiLocatorType4.TAG, "img");
        UiElement4 nonExistentElement =
                UiElement4.getInstance("'Bogus' element", UiLocatorType4.ID, "bogus");
        return new Object[][]{
                {imagePage, imageElement, "src", "https://www.w3schools.com/html/w3schools.jpg"}
                , {imagePage, imageElement, "bogus", null}
                , {imagePage, nonExistentElement, "src", null}
                , {imagePage, imageElement, "alt", "W3Schools.com"}
                , {imagePage, imageElement, "width", "104"}
                , {imagePage, imageElement, "height", "142"}
//                , {stylePage, paragraphElement_2, "style", "color: red;"}
//                , {stylePage, paragraphElement_4, "style", "font-size: 50px;"}
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
        int ordinal = 2;
        Class actual = UiElement4.getInstance(description, locatorType, locatorValue, ordinal).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_parent() {
        Class expected = UiElement4.class;
        UiElement4 parent = UiElement4.getInstance("'Unordered List' element", UiLocatorType4.TAG, "ul");
        Class actual = UiElement4.getInstance("'List Item' element", UiLocatorType4.TAG, "li", parent).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
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

    @Test(dataProvider = "scenarios_getText", enabled = false)
    public void getText(String page, UiElement4 element, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        String actual = element.getText();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getHref", enabled = false)
    public void getHref(String page, UiElement4 element, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        String actual = element.getHref();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_click", enabled = false)
    public void click(String page, UiElement4 element, String expected) {
        expected = expected.contains("http")
                ? expected
                : resources.getPageUrl(expected).replace("file:/", "file:///");
        UiHost4.getInstance().load(resources.getPageUrl(page));
        element.click();
        String actual = UiHost4.getInstance().getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getAttribute", enabled = false)
    public void getAttribute(String page, UiElement4 element, String attribute, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        String actual = element.getAttribute(attribute);
        Assert.assertEquals(actual, expected);
    }

    @Test(enabled = false)
    public void getStyleProperty() {
        String expected = "rgba(255, 0, 0, 1)";
        UiHost4.getInstance().load(resources.getPageUrl(stylePage));
//        String actual = paragraphElement_2.getStyleProperty("color");
//        Assert.assertEquals(actual, expected);
    }
}