package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4StateTests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        boolean isDisplayed = true;
        boolean isNotDisplayed = false;
        final String basicPage = "basic";
        final String listPage = "list";
        final String idPage = "id";
        final String classPage = "class";
        final String tablesPage = "tables";
        final UiElement4 byIdNonexistent =
                UiElement4.getInstance("id nonexistent", UiLocatorType4.ID, "nonexistent");
        final UiElement4 byIdRoot =
                UiElement4.getInstance("id existent", UiLocatorType4.ID, "view");
        final UiElement4 byIdChild =
                UiElement4.getInstance("id on sub-element", UiLocatorType4.ID, "heading-id");
        final UiElement4 byIdDiv =
                UiElement4.getInstance("id on ancestor element", UiLocatorType4.ID, "div-id");
        final UiElement4 byIdDescendent =
                UiElement4.getInstance("id on descendent", UiLocatorType4.ID, "p-id");
        final UiElement4 byTagNonexistent =
                UiElement4.getInstance("tag nonexistent", UiLocatorType4.TAG, "nonexistent");
        final UiElement4 byTagRoot =
                UiElement4.getInstance("tag 'body'", UiLocatorType4.TAG, "body");
        final UiElement4 byTagChild =
                UiElement4.getInstance("tag 'p'", UiLocatorType4.TAG, "p");
        final UiElement4 byTagDescendent =
                UiElement4.getInstance("tag 'li'", UiLocatorType4.TAG, "li");
        final UiElement4 byTagTable = UiElement4.getInstance("tag 'table'", UiLocatorType4.TAG, "table");
        final UiElement4 byClassNonexistent =
                UiElement4.getInstance("class nonexistent", UiLocatorType4.CLASS, "nonexistent");
        final UiElement4 byClassRoot =
                UiElement4.getInstance("class 'root-element'", UiLocatorType4.CLASS, "root-element");
        final UiElement4 byClassChild =
                UiElement4.getInstance("class 'error'", UiLocatorType4.CLASS, "error");
        final UiElement4 byClassDescendent =
                UiElement4.getInstance("class 'names'", UiLocatorType4.CLASS, "names");
        final UiElement4 byTagOrdinalNonexistent =
                UiElement4.getInstance("tag ordinal nonexistent", UiLocatorType4.TAG, "h1", 2);
        final UiElement4 byTagOrdinalChild =
                UiElement4.getInstance("child tag ordinal", UiLocatorType4.TAG, "p", 3);
        final UiElement4 byTagOrdinalDescendent =
                UiElement4.getInstance("descendent tag ordinal", UiLocatorType4.TAG, "li", 4);
        final UiElement4 byTagOrdinalChildDescendent =
                UiElement4.getInstance("child/descendent tag ordinal", UiLocatorType4.TAG, "table", 2);
        final UiElement4 byClassOrdinalNonexistent =
                UiElement4.getInstance("class ordinal nonexistent", UiLocatorType4.CLASS, "error", 3);
        final UiElement4 byClassOrdinalChild =
                UiElement4.getInstance("child class ordinal", UiLocatorType4.CLASS, "error", 2);
        final UiElement4 byClassOrdinalDescendent =
                UiElement4.getInstance("descendent class ordinal", UiLocatorType4.CLASS, "sub-table", 2);
        final UiElement4 byClassChildDescendent =
                UiElement4.getInstance("child/descendent class ordinal", UiLocatorType4.CLASS, "names", 2);
        final UiElement4 byTagNonexistentInRoot =
                UiElement4.getInstance("id nonexistent", UiLocatorType4.ID, "nonexistent", byIdRoot);
        final UiElement4 byClassNonexistentInParent =
                UiElement4.getInstance("class nonexistent in parent", UiLocatorType4.CLASS, "h1", byTagTable);
        final UiElement4 byTagInParentNonexistent =
                UiElement4.getInstance("tag in parent nonexistent", UiLocatorType4.TAG, "h1", byIdNonexistent);
        final UiElement4 byClassTableContainer =
                UiElement4.getInstance("class 'table-container'", UiLocatorType4.CLASS, "table-container");
        final UiElement4 byClassInParent =
                UiElement4.getInstance("class in parent", UiLocatorType4.CLASS, "sub-table", byClassTableContainer);
        final UiElement4 byTagInAncestor =
                UiElement4.getInstance("tag in ancestor", UiLocatorType4.TAG, "td", byClassTableContainer);
        final UiElement4 byTagUl =
                UiElement4.getInstance("'ul' tag", UiLocatorType4.TAG, "ul");
        final UiElement4 byTagOrdinalNonexistentInAncestor =
                UiElement4.getInstance("'li' tag", UiLocatorType4.TAG, "li", 4, byTagUl);
        final UiElement4 byClassOrdinalInParent =
                UiElement4.getInstance("'sub-table' class", UiLocatorType4.CLASS, "sub-table", 2, byClassTableContainer);
        final UiElement4 byTagInParentOrdinal =
                UiElement4.getInstance("'th' tag in ancestor by ordinal", UiLocatorType4.TAG, "th", byClassOrdinalDescendent);
        UiElement4 byTagOrdinal =
                UiElement4.getInstance("by 'div' tag and ordinal", UiLocatorType4.TAG, "div", 2);
        final UiElement4 byClassOrdinalInParentOrdinal =
                UiElement4.getInstance("class 'row-style'", UiLocatorType4.CLASS, "row-style", 3, byTagOrdinal);
        final UiElement4 byFrameNonexistentInRoot = UiElement4.getInstance("'iframe' element", UiLocatorType4.FRAME, "");
        return new Object[][]
                {
                        //  element by id
                        {idPage, byIdNonexistent, isNotDisplayed}
                        , {idPage, byIdRoot, isDisplayed}
                        , {idPage, byIdChild, isDisplayed}
                        , {idPage, byIdDescendent, isDisplayed}
                        //  element by tag
                        , {basicPage, byTagNonexistent, isNotDisplayed}
                        , {basicPage, byTagRoot, isDisplayed}
                        , {basicPage, byTagChild, isDisplayed}
                        , {listPage, byTagDescendent, isDisplayed}
                        //  element by class
                        , {basicPage, byClassNonexistent, isNotDisplayed}
                        , {classPage, byClassRoot, isDisplayed}
                        , {classPage, byClassChild, isDisplayed}
                        , {tablesPage, byClassDescendent, isDisplayed}
                        //  element by tag and ordinal
                        , {basicPage, byTagOrdinalNonexistent, isNotDisplayed}
                        , {classPage, byTagOrdinalChild, isDisplayed}
                        , {listPage, byTagOrdinalDescendent, isDisplayed}
                        , {tablesPage, byTagOrdinalChildDescendent, isDisplayed}
                        //  element by class and ordinal
                        , {basicPage, byClassOrdinalNonexistent, isNotDisplayed}
                        , {classPage, byClassOrdinalChild, isDisplayed}
                        , {tablesPage, byClassOrdinalDescendent, isDisplayed}
                        , {tablesPage, byClassChildDescendent, isDisplayed}
                        //  element as descendent
                        , {basicPage, byTagNonexistentInRoot, isNotDisplayed}
                        , {basicPage, byClassNonexistentInParent, isNotDisplayed}
                        , {basicPage, byTagInParentNonexistent, isNotDisplayed}
                        , {tablesPage, byClassInParent, isDisplayed}
                        , {tablesPage, byTagInAncestor, isDisplayed}
                        //  element as nth descendent of ancestor
                        , {listPage, byTagOrdinalNonexistentInAncestor, isNotDisplayed}
                        , {tablesPage, byClassOrdinalInParent, isDisplayed}
                        , {tablesPage, byTagInParentOrdinal, isDisplayed}
                        , {tablesPage, byClassOrdinalInParentOrdinal, isDisplayed}
                        //  iframe element
                        , {basicPage, byFrameNonexistentInRoot, isNotDisplayed}
                };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_isDisplayed")
    public void isDisplayed(String page, UiElement4 element, boolean expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}