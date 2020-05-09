package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.softwareonpurpose.uinavigator.UiElement;

public class MockView extends UiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    public MockView() {
        super(VIEW_URI, UiElement.getInstance("'Mock' view", UiLocatorType.TAG, "body"));
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

    public void clickButton() {
        getButtonElement().click();
    }

    private UiElement getButtonElement() {
        final String description = "Button";
        final String locatorValue = "button-1";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, (UiElement) this.getElement());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }

    public void setUsername(String value) {
        getUsernameElement().set(value);
    }

    private UiElement getUsernameElement() {
        return UiElement.getInstance("Username", UiLocatorType.NAME, "user_name", (UiElement) this.getElement());
    }

    public String getUsernameText() {
        return getUsernameElement().getText();
    }
}
