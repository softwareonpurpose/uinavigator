package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiView4Tests {
    @SuppressWarnings("rawtypes")
    @Test
    public void expect(){
        Class expected = UiVew4.class;
        Class actual = BasicView.expect().getClass().getSuperclass();
        Assert.assertEquals(actual, expected);
    }
}
