package com.softwareonpurpose.uinavigator;

public class BasicView extends UiView4 {
    public static BasicView expect() {
        return UiView4.expect(BasicView.class);
    }

    @Override
    protected boolean confirmState() {
        return false;
    }
}
