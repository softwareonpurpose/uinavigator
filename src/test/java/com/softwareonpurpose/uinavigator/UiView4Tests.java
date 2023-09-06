package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.test.view.BasicView;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiView4Tests extends TestResources {
    @SuppressWarnings("rawtypes")
    @Test
    public void expect() {
        Class expected_class = BasicView.class;
        Class expected_inheritance = UiView4.class;
        Class actual_class = BasicView.expect().getClass();
        Class actual_inheritance = actual_class.getSuperclass();
        Assert.assertEquals(actual_class, expected_class);
        Assert.assertEquals(actual_inheritance, expected_inheritance);
    }
}
