package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeNonExistentAttributeTests extends TestClass {
    @Test
    public void testExecute_nonexistentAttribute() {
        MockView.directNav(host);
        String description = "Non Existent";
        final String locatorValue = "name";
        final String attribute = "data-nonexistent";
        final String attributeValue = "non-existent";
        final WebElementGetByLocatorAttribute getBehavior = WebElementGetByLocatorAttribute.getInstance(
                description, UiLocatorType.ID, locatorValue, attribute, attributeValue, UiDriverGet.getInstance());
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null for nonexistent attribute");
    }
}
