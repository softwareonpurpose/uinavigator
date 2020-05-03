package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;

public class MockView extends UiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    public MockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"));
    }

    public static MockView directNav() {
        MockView view = construct(MockView.class);
        view.load();
        return UiView.expect(view.getClass());
    }

    public static UiView directNav(String relativeUrl) {
        MockView view = construct(MockView.class);
        view.load(relativeUrl);
        return UiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
