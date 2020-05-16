package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

@Test
public class WebGetListByLocatorOrdinalParentNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistentOrdinal() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        final int expected = 0;
        final WebGetElementListByLocatorOrdinalParent getListBehavior =
                WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", 5, null, host);
        Collection<UiElement> actual = getListBehavior.execute();
        host.quit();
        Assert.assertEquals(actual.size(), expected, "Failed to return an empty list for nonexistent ordinal");
    }
}
