package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SuppressWarnings("ConstantConditions")
@Test
public class UiElement4Tests {

    private static final UiElement4 bodyElement = UiElement4.getInstance("'body' element'", UiLocatorType4.TAG, "body");
    private static final UiElement4 headingElement = UiElement4.getInstance("'heading' element'", UiLocatorType4.TAG, "h1");
    private static final UiElement4 secondParagraphElement = UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, "p", 2);
    private static final UiElement4 anchorElement = UiElement4.getInstance("'anchor' element", UiLocatorType4.TAG, "a");
    private static final UiElement4 unorderedListElement = UiElement4.getInstance("'unordered list", UiLocatorType4.TAG, "ul");
    private static final UiElement4 unorderedListItemElement = UiElement4.getInstance("'list item' element", UiLocatorType4.TAG, "li", unorderedListElement);
    private static final UiElement4 paragraphElement = UiElement4.getInstance("'p' tag", UiLocatorType4.TAG, "p");
    private static final UiElement4 divElement = UiElement4.getInstance("'div' tag", UiLocatorType4.TAG, "div");
    private static final UiElement4 orderedListElement = UiElement4.getInstance("'ordered list", UiLocatorType4.TAG, "ol");
    private static final UiElement4 nthOrderedListItemElement = UiElement4.getInstance("'list item' element", UiLocatorType4.TAG, "li", 3, orderedListElement);

    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        boolean isDisplayed = true;
        boolean isNotDisplayed = false;
        return new Object[][]
                {
                        {"basic", bodyElement, isDisplayed}
                        , {"basic", headingElement, isDisplayed}
                        , {"basic", secondParagraphElement, isNotDisplayed}
                        , {"paragraphs", secondParagraphElement, isDisplayed}
                        , {"link", anchorElement, isDisplayed}
                        , {"list", unorderedListItemElement, isDisplayed}
                        , {"list", nthOrderedListItemElement, isDisplayed}
                };
    }

    @DataProvider
    public static Object[][] scenarios_getText() {
        String basicPage = "basic";
        String basicLink = "link";
        String list = "list";
        String firstHeading = "My First Heading";
        String firstParagraph = "My first paragraph.";
        String fullBody = String.format("%s\n%s", firstHeading, firstParagraph);
        return new Object[][]{
                {basicPage, headingElement, firstHeading}
                , {basicPage, paragraphElement, firstParagraph}
                , {basicPage, bodyElement, fullBody}
                , {basicPage, divElement, null}
                , {basicLink, anchorElement, "This is a link"}
                , {list, unorderedListItemElement, "Coffee"}
                , {list, nthOrderedListItemElement, "Milk"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getHref() {
        return new Object[][]{
                {"link", UiElement4.getInstance("'Anchor' tag", UiLocatorType4.TAG, "a"), "https://www.w3schools.com/"}
                , {"basic", UiElement4.getInstance("'Anchor' tag", UiLocatorType4.TAG, "a"), null}
                , {"link", UiElement4.getInstance("'Paragraph' tag", UiLocatorType4.TAG, "p"), null}
        };
    }

    @DataProvider
    public static Object[][] scenarios_click() {
        return new Object[][]{
                {"link", UiElement4.getInstance("'This is a link' anchor", UiLocatorType4.TAG, "a"), "https://www.w3schools.com/"}
                , {"basic", UiElement4.getInstance("'This is a link' anchor", UiLocatorType4.TAG, "a"), "basic"}
                , {"basic", UiElement4.getInstance("'heading' element'", UiLocatorType4.TAG, "h1"), "basic"}
        };
    }

    @DataProvider
    public static Object[][] scenarios_getAttribute() {
        UiElement4 imageElement =
                UiElement4.getInstance("'Image' element", UiLocatorType4.TAG, "img");
        UiElement4 nonExistentElement =
                UiElement4.getInstance("'Bogus' element", UiLocatorType4.ID, "bogus");
        return new Object[][]{
                {"image", imageElement, "src", "https://www.w3schools.com/html/w3schools.jpg"}
                , {"image", imageElement, "bogus", null}
                , {"image", nonExistentElement, "src", null}
                , {"image", imageElement, "alt", "W3Schools.com"}
                , {"image", imageElement, "width", "104"}
                , {"image", imageElement, "height", "142"}
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
        UiHost4.getInstance().load(getPageUrl(page));
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getText")
    public void getText(String page, UiElement4 element, String expected) {
        UiHost4.getInstance().load(getPageUrl(page));
        String actual = element.getText();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getHref")
    public void getHref(String page, UiElement4 element, String expected) {
        UiHost4.getInstance().load(getPageUrl(page));
        String actual = element.getHref();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_click")
    public void click(String page, UiElement4 element, String expected) {
        expected = expected.contains("http")
                ? expected
                : getPageUrl(expected).replace("file:/", "file:///");
        UiHost4.getInstance().load(getPageUrl(page));
        element.click();
        String actual = UiHost4.getInstance().getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "scenarios_getAttribute")
    public void getAttribute(String page, UiElement4 element, String attribute, String expected) {
        UiHost4.getInstance().load(getPageUrl(page));
        String actual = element.getAttribute(attribute);
        Assert.assertEquals(actual, expected);
    }

    private String getResourceFilename(String resourceFilename) {
        return getClass().getResource(resourceFilename).toString();
    }

    private String getPageUrl(String page) {
        return getResourceFilename(String.format("/%s.html", page));
    }
}