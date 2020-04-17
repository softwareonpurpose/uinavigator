package com.softwareonpurpose.uinavigator.web.mock;

import com.softwareonpurpose.uinavigator.web.WebUiElement;
import com.softwareonpurpose.uinavigator.web.WebUiView;
import org.openqa.selenium.By;

public class MockView extends WebUiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    public MockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", new By.ByTagName("body")));
    }

    public static MockView directNav() {
        MockView view = construct(MockView.class);
        view.load();
        return WebUiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }
}
