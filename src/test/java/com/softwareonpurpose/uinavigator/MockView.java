package com.softwareonpurpose.uinavigator;

public class MockView extends UiView {
    private static final String DESCRIPTION = "'Mock' view";
    private static final String LOCATOR_VALUE = "body";
    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
    private final UiHost host;

    public MockView(UiHost host) {
        super(VIEW_URI, UiElement.getInstance(DESCRIPTION, UiLocatorType.TAG, LOCATOR_VALUE, host), host);
        this.host = host;
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
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement(), host);
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }

    public void setUsername(String value) {
        getUsernameElement().set(value);
    }

    private UiElement getUsernameElement() {
        return UiElement.getInstance("Username", UiLocatorType.NAME, "user_name", this.getElement(), host);
    }

    public String getUsernameText() {
        return getUsernameElement().getText();
    }
}
