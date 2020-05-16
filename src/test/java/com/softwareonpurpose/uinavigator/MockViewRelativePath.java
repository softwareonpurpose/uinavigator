package com.softwareonpurpose.uinavigator;

public class MockViewRelativePath extends UiView {
    private static final String VIEW_URI = "http://www.google.com";
    private static final String LOCATOR_VALUE = "body";

    public MockViewRelativePath(UiHost host) {
        super(VIEW_URI,
                UiElement.getInstance("'Relative Path Mock' view", UiLocatorType.TAG, LOCATOR_VALUE, host), host);
    }

    public static MockViewRelativePath directNav(UiHost host) {
        MockViewRelativePath view = construct(MockViewRelativePath.class, host);
        view.load("search?q=mousetrap");
        return UiView.expect(view.getClass(), host);
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
