package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.softwareonpurpose.uinavigator.UiElement;

public class MockViewRelativePath extends UiView {
    private static final String VIEW_URI = "http://www.google.com";
    private static final String LOCATOR_VALUE = "body";

    public MockViewRelativePath() {
        super(VIEW_URI,
                UiElement.getInstance("'Relative Path Mock' view", UiLocatorType.TAG, LOCATOR_VALUE));
    }

    public static MockViewRelativePath directNav() {
        MockViewRelativePath view = construct(MockViewRelativePath.class);
        view.load("search?q=mousetrap");
        return UiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
