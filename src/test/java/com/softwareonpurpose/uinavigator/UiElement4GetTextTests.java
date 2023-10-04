package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4GetTextTests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_getText() {
        final String myHeader = "My Header";
        final String nestedParagraph = "A nested paragraph";
        final String idPageContent = "The id Attribute\n" +
                "Use CSS to style an element with the id \"myHeader\":\n" +
                myHeader + "\n" +
                nestedParagraph;
        final String firstParagraph = "My first paragraph.";
        final String firstHeading = "My First Heading";
        final String coffeeUnordered = "Coffee (unordered)";
        final String different = "I am different.";
        final String aParagraph = "This is a paragraph.";
        final String differentToo = "I am different too.";
        final String firstname = "Firstname";
        final String lastname = "Lastname";
        final String age = "Age";
        final String tableHeading2 = String.format("%s%d %s%d %s%d", firstname, 2, lastname, 2, age, 2);
        final String tableHeading3 = String.format("%s%d %s%d %s%d", firstname, 3, lastname, 3, age, 3);
        final String tableHeading4 = String.format("%s%d %s%d %s%d", firstname, 4, lastname, 4, age, 4);
        final String jill = "Jill";
        final String smith = "Smith";
        final String eve = "Eve";
        final String jackson = "Jackson";
        final String john = "John";
        final String doe = "Doe";
        final String jill2FirstName = String.format("%s2", jill);
        final String jill2 = String.format("%s%d %s%d %d", jill, 2, smith, 2, 50);
        final String jill3 = String.format("%s%d %s%d %d", jill, 3, smith, 3, 50);
        final String jill4 = String.format("%s%d %s%d %d", jill, 4, smith, 4, 50);
        final String eve2 = String.format("%s%d %s%d %d", eve, 2, jackson, 2, 94);
        final String eve3 = String.format("%s%d %s%d %d", eve, 3, jackson, 3, 94);
        final String eve4 = String.format("%s%d %s%d %d", eve, 4, jackson, 4, 94);
        final String joe2 = String.format("%s%d %s%d %d", john, 2, doe, 2, 80);
        final String joe3 = String.format("%s%d %s%d %d", john, 3, doe, 3, 80);
        final String joe4 = String.format("%s%d %s%d %d", john, 4, doe, 4, 80);
        final String coffeeOrdered = "Coffee (ordered)";
        final String table2 = tableHeading2 + "\n" + jill2 + "\n" + eve2 + "\n" + joe2;
        final String table3 = tableHeading3 + "\n" + jill3 + "\n" + eve3 + "\n" + joe3;
        final String table4 = tableHeading4 + "\n" + jill4 + "\n" + eve4 + "\n" + joe4;
        final UiElement4 byIdNonexistent =
                UiElement4.getInstance("id nonexistent", UiLocatorType4.ID, "nonexistent");
        final UiElement4 byIdRoot =
                UiElement4.getInstance("id existent", UiLocatorType4.ID, "view");
        final UiElement4 byIdChild =
                UiElement4.getInstance("id on sub-element", UiLocatorType4.ID, "heading-id");
        final UiElement4 byIdDescendent =
                UiElement4.getInstance("id on descendent", UiLocatorType4.ID, "p-id");
        final UiElement4 byIdDiv =
                UiElement4.getInstance("id on ancestor element", UiLocatorType4.ID, "div-id");
        final UiElement4 byTagNonexistent =
                UiElement4.getInstance("tag nonexistent", UiLocatorType4.TAG, "nonexistent");
        final UiElement4 byTagRoot =
                UiElement4.getInstance("tag 'body'", UiLocatorType4.TAG, "body");
        final UiElement4 byTagChild =
                UiElement4.getInstance("tag 'p'", UiLocatorType4.TAG, "p");
        final UiElement4 byTagDescendent =
                UiElement4.getInstance("tag 'li'", UiLocatorType4.TAG, "li");
        final UiElement4 byClassNonexistent =
                UiElement4.getInstance("class nonexistent", UiLocatorType4.CLASS, "nonexistent");
        final UiElement4 byClassRootElement =
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
        final UiElement4 byTagNonexistentInParent =
                UiElement4.getInstance("tag nonexistent in parent", UiLocatorType4.TAG, "h1", byIdDiv);
        final UiElement4 byTagInParentNonexistent =
                UiElement4.getInstance("tag in parent nonexistent", UiLocatorType4.TAG, "h1", byIdNonexistent);
        final UiElement4 byTagInParent =
                UiElement4.getInstance("tag in parent", UiLocatorType4.TAG, "p", byIdDiv);
        final UiElement4 byClassTableContainer =
                UiElement4.getInstance("class 'table-container'", UiLocatorType4.CLASS, "table-container");
        final UiElement4 byTagInAncestor =
                UiElement4.getInstance("tag in ancestor", UiLocatorType4.TAG, "td", byClassTableContainer);
        final String basicPage = "basic";
        final String listPage = "list";
        final String idPage = "id";
        final String classPage = "class";
        final String tablesPage = "tables";
        return new Object[][]{
                //  element by id
                {idPage, byIdNonexistent, null}
                , {idPage, byIdRoot, idPageContent}
                , {idPage, byIdChild, myHeader}
                , {idPage, byIdDescendent, nestedParagraph}
                //  element by tag
                , {basicPage, byTagNonexistent, null}
                , {basicPage, byTagRoot, firstHeading + "\n" + firstParagraph}
                , {basicPage, byTagChild, firstParagraph}
                , {listPage, byTagDescendent, coffeeUnordered}
                //  element by class
                , {basicPage, byClassNonexistent, null}
                , {classPage, byClassRootElement,
                aParagraph + "\n" + aParagraph + "\n" + different + "\n" + aParagraph + "\n" + differentToo}
                , {classPage, byClassChild, different}
                , {tablesPage, byClassDescendent, table2}
                //  element by tag and ordinal
                , {basicPage, byTagOrdinalNonexistent, null}
                , {classPage, byTagOrdinalChild, different}
                , {listPage, byTagOrdinalDescendent, coffeeOrdered}
                , {tablesPage, byTagOrdinalChildDescendent, table2}
                //  element by class and ordinal
                , {basicPage, byClassOrdinalNonexistent, null}
                , {classPage, byClassOrdinalChild, differentToo}
                , {tablesPage, byClassOrdinalDescendent, table3}
                , {tablesPage, byClassChildDescendent, table4}
                //  element as descendent
                , {basicPage, byTagNonexistentInRoot, null}
                , {basicPage, byTagNonexistentInParent, null}
                , {basicPage, byTagInParentNonexistent, null}
                , {idPage, byTagInParent, nestedParagraph}
                , {tablesPage, byTagInAncestor, jill2FirstName}
        };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "scenarios_getText")
    public void getText(String page, UiElement4 element, String expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        String actual = element.getText();
        Assert.assertEquals(actual, expected);
    }
}