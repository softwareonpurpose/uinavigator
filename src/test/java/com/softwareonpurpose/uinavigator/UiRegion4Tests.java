package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.test.view.region.UnorderedListRegion;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiRegion4Tests {

    private final TestResources resources = TestResources.getInstance();

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(enabled = false)
    public void isDisplayed() {
        UiElement4 parent = UiElement4.getInstance("Page", UiLocatorType4.TAG, "body");
        UiHost4.getInstance().load(resources.getPageUrl("list"));
        Boolean expected = true;
        Boolean actual = UnorderedListRegion.getInstance(parent).isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
