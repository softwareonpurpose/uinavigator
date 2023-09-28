package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    private static final TestResources resources = TestResources.getInstance();
    private static final UiElement4 byIdNonexistent = UiElement4.getInstance("invalid id", UiLocatorType4.ID, "non-existent-id");
    private static final UiElement4 byTagNonexistent = UiElement4.getInstance("invalid id", UiLocatorType4.TAG, "non-existent-tag");
    private static final UiElement4 byClassNonexistent = UiElement4.getInstance("invalid id", UiLocatorType4.CLASS, "non-existent-class");
    private static final UiElement4 byIdDiv = UiElement4.getInstance("'div' element", UiLocatorType4.ID, "div-id");
    private static final UiElement4 byTagBody = UiElement4.getInstance("'body' element'", UiLocatorType4.TAG, "body");
    private static final UiElement4 byTagH1 = UiElement4.getInstance("'heading' element'", UiLocatorType4.TAG, "h1");
    private static final UiElement4 byTagA = UiElement4.getInstance("'anchor' element", UiLocatorType4.TAG, "a");
    private static final UiElement4 byTagUl = UiElement4.getInstance("'unordered list", UiLocatorType4.TAG, "ul");
    private static final UiElement4 byTagP = UiElement4.getInstance("'p' tag", UiLocatorType4.TAG, "p");
    private static final UiElement4 byTagDiv = UiElement4.getInstance("'div' tag", UiLocatorType4.TAG, "div");
    private static final UiElement4 byTagOl = UiElement4.getInstance("'ordered list", UiLocatorType4.TAG, "ol");
    private static final UiElement4 byTagMeta = UiElement4.getInstance("'meta' element", UiLocatorType4.TAG, "meta");
    private static final UiElement4 byTagBr = UiElement4.getInstance("'break' element", UiLocatorType4.TAG, "br");
    private static final UiElement4 byTagPre = UiElement4.getInstance("'pre' element", UiLocatorType4.TAG, "pre");
    private static final UiElement4 byClassNames = UiElement4.getInstance("class 'names'", UiLocatorType4.CLASS, "names");
    private static final UiElement4 byTagOrdinal2 = UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, "p", 2);
    private static final UiElement4 byTagOrdinal4 = UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, "p", 4);
    private static final UiElement4 byClassOrdinal2 = UiElement4.getInstance("paragraph by class and ordinal", UiLocatorType4.CLASS, "error", 2);
    private static final UiElement4 byIdAncestorById = UiElement4.getInstance("paragraph by id", UiLocatorType4.ID, "p-id", byIdDiv);
    private static final UiElement4 byIdAncestorByTag = UiElement4.getInstance("'paragraph' element", UiLocatorType4.ID, "p-id", byTagDiv);
    private static final UiElement4 byTagAncestorById = UiElement4.getInstance("'p' tag", UiLocatorType4.TAG, "p", byIdDiv);
    private static final UiElement4 byTagAncestorByTag = UiElement4.getInstance("'list item' element", UiLocatorType4.TAG, "li", byTagUl);
    //  TODO:   byTagAncestorByTagOrdinal
    private static final UiElement4 byTagAncestorByClass = UiElement4.getInstance("'th' element", UiLocatorType4.TAG, "th", byClassNames);
    //  TODO:   Rework this line to use previously defined fields
    //   private static final UiElement4 byTagAncestorByClassOrdinal3 = UiElement4.getInstance("'th' element", UiLocatorType4.TAG, "th", byClassOrdinal3);

    //  TODO:   byTagOrdinalAncestorById
    private static final UiElement4 byTagOrdinal3AncestorByTag = UiElement4.getInstance("'list item' element", UiLocatorType4.TAG, "li", 3, byTagOl);
    //  TODO:   byTagOrdinalAncestorByTagOrdinal
    private static final UiElement4 byTagOrdinal3AncestorByClass = UiElement4.getInstance("nth 'th' element", UiLocatorType4.TAG, "th", 3, byClassNames);
    //  TODO:   Rework this line to use previously defined fields
    //  private static final UiElement4 byTagOrdinal2AncestorByClassOrdinal3 = UiElement4.getInstance("'th' element", UiLocatorType4.TAG, "th", 2, byClassOrdinal3);
    //  TODO:   byClassAncestorById
    private static final UiElement4 byClassAncestorByTag = UiElement4.getInstance("'paragraph' element", UiLocatorType4.CLASS, "error", byTagDiv);
    //  TODO:   byClassAncestorByTagOrdinal
    //  TODO:   byClassAncestorByClass
    //  TODO:   byClassAncestorByClassOrdinal
    //  TODO:   byClassOrdinalAncestorById
    private static final UiElement4 byClassOrdinal2AncestorByTag = UiElement4.getInstance("paragraph by class and ordinal", UiLocatorType4.CLASS, "error", 2, byTagBody);
    //  TODO:   byClassOrdinalAncestorByTagOrdinal
    //  TODO:   Rework this line to use previously defined fields
    //  private static final UiElement4 byClassOrdinal2AncestorByClass = UiElement4.getInstance("class 'names'", UiLocatorType4.CLASS, "names", 2, byClassNewTables);
    //  TODO:   byClassOrdinalAncestorByClassOrdinal
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
    private static final String red = "rgba(255, 0, 0, 1)";
    private static final String tableClass1Page = "tableClass1";

    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        boolean isDisplayed = true;
        boolean isNotDisplayed = false;
        return new Object[][]
                {
                        {idPage, byIdNonexistent, isNotDisplayed}
                        , {idPage, byIdDiv, isDisplayed}
                        , {basicPage, byTagBody, isDisplayed}
                        , {listPage, byTagUl, isDisplayed}
                        , {tableClass1Page, byClassNames, isDisplayed}
                        , {basicPage, byTagOrdinal2, isNotDisplayed}
                        , {classPage, byClassOrdinal2, isDisplayed}
                        , {idPage, byIdAncestorById, isDisplayed}
                        , {idPage, byIdAncestorByTag, isDisplayed}
                        , {idPage, byTagAncestorById, isDisplayed}
                        , {listPage, byTagAncestorByTag, isDisplayed}
                        , {paragraphsPage, byTagOrdinal2, isDisplayed}
                        , {listPage, byTagOrdinal3AncestorByTag, isDisplayed}
                        , {headPage, byTagMeta, isNotDisplayed}
                        , {breakPage, byTagBr, isNotDisplayed}
                        , {prePage, byTagPre, isDisplayed}
                        , {classPage, byClassOrdinal2AncestorByTag, isDisplayed}
                        , {tableClass1Page, byTagAncestorByClass, isDisplayed}
                        , {tableClass1Page, byTagOrdinal3AncestorByClass, isDisplayed}
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
        String different = "I am different.";
        String differentToo = "I am different too.";
        String parentElementText = """
                Table 1 Firstname Table 1 Lastname Table 1 Age
                Table 1 Jill Table 1 Smith Table 1 50
                Table 1 Eve Table 1 Jackson Table 1 94
                Table 1 John Table 1 Doe Table 1 80""";
        return new Object[][]{
                {basicPage, byTagH1, firstHeading}
                , {basicPage, byTagP, firstParagraph}
                , {basicPage, byTagBody, fullBody}
                , {basicPage, byTagDiv, null}
                , {linkPage, byTagA, "This is a link"}
                , {listPage, byTagAncestorByTag, "Coffee"}
                , {listPage, byTagOrdinal3AncestorByTag, "Milk"}
                , {breakPage, byTagP, "This is\na paragraph\nwith line breaks."}
                , {prePage, byTagPre, myBonnie}
                , {tableClass1Page, byClassNames, parentElementText}
                , {classPage, byClassOrdinal2, differentToo}
                , {classPage, byClassOrdinal2AncestorByTag, differentToo}
                , {classPage, byClassAncestorByTag, differentToo}
                , {tableClass1Page, byTagAncestorByClass, "Table 1 Firstname"}
                , {tableClass1Page, byTagOrdinal3AncestorByClass, "Table 1 Age"}
                , {tableClass1Page, null, "Table 2 Firstname"}
                , {tableClass1Page, null, "Table 2 Lastname"}
                , {tableClass1Page, null, "Table 3 Firstname"}
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
                , {stylePage, byTagOrdinal2, "style", "color: red;"}
                , {stylePage, byTagOrdinal4, "style", "font-size: 50px;"}
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

    @Test
    public void getStyleProperty() {
        //noinspection UnnecessaryLocalVariable
        String expected = red;
        UiHost4.getInstance().load(resources.getPageUrl(stylePage));
        String actual = byTagOrdinal2.getStyleProperty("color");
        Assert.assertEquals(actual, expected);
    }
}