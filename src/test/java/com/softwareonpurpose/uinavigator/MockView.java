package com.softwareonpurpose.uinavigator;

public class MockView extends UiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    public MockView(UiHost host) {
        super(VIEW_URI, UiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"), host);
    }

    public static MockView directNav(UiHost host) {
        MockView view = construct(MockView.class, host);
        view.load();
        return UiView.expect(view.getClass(), host);
    }

    public static UiView directNav(String relativeUrl, UiHost host) {
        MockView view = construct(MockView.class, host);
        view.load(relativeUrl);
        return UiView.expect(view.getClass(), host);
    }

    public void clickButton() {
        getButtonElement().click();
    }

    private UiElement getButtonElement() {
        final String description = "Button";
        final String locatorValue = "button-1";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }

    public void setUsername(String value) {
        getUsernameElement().set(value);
    }

    private UiElement getUsernameElement() {
        return UiElement.getInstance("Username", UiLocatorType.NAME, "user_name", this.getElement());
    }

    public String getUsernameText() {
        return getUsernameElement().getText();
    }
}
