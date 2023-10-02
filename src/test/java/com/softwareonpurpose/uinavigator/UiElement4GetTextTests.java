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
        String myHeader = "My Header";
        String nestedParagraph = "A nested paragraph";
        String idPageContent = "The id Attribute\n" +
                "Use CSS to style an element with the id \"myHeader\":\n" +
                myHeader + "\n" +
                nestedParagraph;
        String firstParagraph = "My first paragraph.";
        String firstHeading = "My First Heading";
        String coffeeUnordered = "Coffee (unordered)";
        String different = "I am different.";
        String aParagraph = "This is a paragraph.";
        String differentToo = "I am different too.";
        String tableHeading = "Firstname Lastname Age";
        String jill = "Jill Smith 50";
        String eve = "Eve Jackson 94";
        String joe = "John Doe 80";
        final UiElement4 byIdNonexistent =
                UiElement4.getInstance("id nonexistent", UiLocatorType4.ID, "nonexistent");
        final UiElement4 byIdView =
                UiElement4.getInstance("id existent", UiLocatorType4.ID, "view");
        final UiElement4 byIdHeading =
                UiElement4.getInstance("id on sub-element", UiLocatorType4.ID, "heading-id");
        final UiElement4 byIdP =
                UiElement4.getInstance("id on descendent", UiLocatorType4.ID, "p-id");
        final UiElement4 byTagNonexistent =
                UiElement4.getInstance("tag nonexistent", UiLocatorType4.TAG, "nonexistent");
        final UiElement4 byTagBody =
                UiElement4.getInstance("tag 'body'", UiLocatorType4.TAG, "body");
        final UiElement4 byTagP =
                UiElement4.getInstance("tag 'p'", UiLocatorType4.TAG, "p");
        final UiElement4 byTagLi =
                UiElement4.getInstance("tag 'li'", UiLocatorType4.TAG, "li");
        final UiElement4 byClassNonexistent =
                UiElement4.getInstance("class nonexistent", UiLocatorType4.CLASS, "nonexistent");
        final UiElement4 byClassRootElement =
                UiElement4.getInstance("class 'root-element'", UiLocatorType4.CLASS, "root-element");
        final UiElement4 byClassError =
                UiElement4.getInstance("class 'error'", UiLocatorType4.CLASS, "error");
        final UiElement4 byClassNames =
                UiElement4.getInstance("class 'names'", UiLocatorType4.CLASS, "names");
        final String basicPage = "basic";
        final String listPage = "list";
        final String idPage = "id";
        final String classPage = "class";
        final String tablesPage = "tables";
        return new Object[][]{
                {idPage, byIdNonexistent, null}
                , {idPage, byIdView, idPageContent}
                , {idPage, byIdHeading, myHeader}
                , {idPage, byIdP, nestedParagraph}
                , {basicPage, byTagNonexistent, null}
                , {basicPage, byTagBody, firstHeading + "\n" + firstParagraph}
                , {basicPage, byTagP, firstParagraph}
                , {listPage, byTagLi, coffeeUnordered}
                , {basicPage, byClassNonexistent, null}
                , {classPage, byClassRootElement,
                aParagraph + "\n" + aParagraph + "\n" + different + "\n" + aParagraph + "\n" + differentToo}
                , {classPage, byClassError, different}
                , {tablesPage, byClassNames, tableHeading + "\n" + jill + "\n" + eve + "\n" + joe}
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