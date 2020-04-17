package com.softwareonpurpose.uinavigator.web.mock;

import com.softwareonpurpose.uinavigator.web.WebUiElement;
import com.softwareonpurpose.uinavigator.web.WebUiView;
import org.openqa.selenium.By;

public class BrokenMockView extends WebUiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    protected BrokenMockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", new By.ByTagName("body")));
    }

    public static BrokenMockView directNav() {
        BrokenMockView view = construct(BrokenMockView.class);
        view.load();
        return WebUiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
