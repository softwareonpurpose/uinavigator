package com.softwareonpurpose.uinavigator.test.view;

import com.softwareonpurpose.uinavigator.UiView4;

public class BasicView extends UiView4 {
    public static BasicView expect() {
        return UiView4.expect(BasicView.class);
    }

    @Override
    protected boolean confirmState() {
        return false;
    }
}
