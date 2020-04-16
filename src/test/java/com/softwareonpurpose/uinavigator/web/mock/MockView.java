package com.softwareonpurpose.uinavigator.web.mock;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.web.WebUiElement;
import com.softwareonpurpose.uinavigator.web.WebUiView;

public class MockView extends WebUiView {

    private static final String VIEW_URI = "http://www.google.com";

    @SuppressWarnings("WeakerAccess")
    public MockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"));
    }

    public static WebUiView directNav() {
        MockView view = new MockView();
        view.load();
        return WebUiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
