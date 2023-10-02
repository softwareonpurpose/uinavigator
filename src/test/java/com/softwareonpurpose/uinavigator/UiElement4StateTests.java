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