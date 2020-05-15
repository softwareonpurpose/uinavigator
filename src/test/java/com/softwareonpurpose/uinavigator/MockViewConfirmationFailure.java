package com.softwareonpurpose.uinavigator;

public class MockViewConfirmationFailure extends UiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    public MockViewConfirmationFailure(UiHost host) {
        super(VIEW_URI, UiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"), host);
    }

    public static MockViewConfirmationFailure directNav(UiHost host) {
        MockViewConfirmationFailure view = construct(MockViewConfirmationFailure.class, host);
        view.load();
        return UiView.expect(view.getClass(), host);
    }

    public static UiView directNav(String relativeUrl, UiHost host) {
        MockViewConfirmationFailure view = construct(MockViewConfirmationFailure.class, host);
        view.load(relativeUrl);
        return UiView.expect(view.getClass(), host);
    }

    @Override
    protected boolean confirmElementStates() {
        boolean confirmed = this.getElement().waitUntilVisible();
        confirmed &= getNonExistentElement().isDisplayed();
        return confirmed;
    }

    private UiElement getNonExistentElement() {
        return UiElement.getInstance("Non Existent", UiLocatorType.ID, "non-existent", this.getElement());
    }
}
