package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;

public class RelativePathMockView extends WebUiView {

    private static final String VIEW_URI = "http://www.google.com";

    public RelativePathMockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", new By.ByTagName("body")));
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
