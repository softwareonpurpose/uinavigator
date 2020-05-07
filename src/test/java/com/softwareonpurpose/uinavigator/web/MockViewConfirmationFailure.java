package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;

public class MockViewConfirmationFailure extends UiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    public MockViewConfirmationFailure() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"));
    }

    public static MockViewConfirmationFailure directNav() {
        MockViewConfirmationFailure view = construct(MockViewConfirmationFailure.class);
        view.load();
        return UiView.expect(view.getClass());
    }

    public static UiView directNav(String relativeUrl) {
        MockViewConfirmationFailure view = construct(MockViewConfirmationFailure.class);
        view.load(relativeUrl);
        return UiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        boolean confirmed = this.getElement().waitUntilVisible();
        confirmed &= getNonExistentElement().isDisplayed();
        return confirmed;
    }

    private WebUiElement getNonExistentElement() {
        return WebUiElement.getInstance("Non Existent", UiLocatorType.ID, "non-existent", (WebUiElement) this.getElement());
    }
}
