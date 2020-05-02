package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;

public class MockViewRelativePath extends WebUiView {
    private static final String VIEW_URI = "http://www.google.com";
    private static final String LOCATOR_VALUE = "body";

    public MockViewRelativePath() {
        super(VIEW_URI,
                WebUiElement.getInstance("'Relative Path Mock' view", UiLocatorType.TAG, LOCATOR_VALUE));
    }

    public static MockViewRelativePath directNav() {
        MockViewRelativePath view = construct(MockViewRelativePath.class);
        view.load("search?q=mousetrap");
        return WebUiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
