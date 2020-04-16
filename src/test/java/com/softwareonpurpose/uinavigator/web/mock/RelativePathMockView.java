package com.softwareonpurpose.uinavigator.web.mock;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.web.WebUiElement;
import com.softwareonpurpose.uinavigator.web.WebUiView;

public class RelativePathMockView extends WebUiView {

    private static final String VIEW_URI = "http://www.google.com";

    public RelativePathMockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"));
    }

    public static RelativePathMockView directNav() {
        RelativePathMockView view = construct(RelativePathMockView.class);
        view.load("search?q=mousetrap");
        return WebUiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
