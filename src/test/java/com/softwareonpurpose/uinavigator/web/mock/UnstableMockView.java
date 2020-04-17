package com.softwareonpurpose.uinavigator.web.mock;

import com.softwareonpurpose.uinavigator.web.WebUiElement;
import com.softwareonpurpose.uinavigator.web.WebUiView;
import org.openqa.selenium.By;

public class UnstableMockView extends WebUiView {

    private static final String VIEW_URI = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";

    public UnstableMockView() {
        super(VIEW_URI, WebUiElement.getInstance("'Mock' view", new By.ByTagName("body")));
    }

    public static UnstableMockView directNav() {
        UnstableMockView view = construct(UnstableMockView.class);
        view.load();
        return WebUiView.expect(view.getClass());
    }

    @Override
    protected boolean confirmElementStates() {
        boolean confirmed = this.getElement().waitUntilVisible();
        final WebUiElement nonExistentElement =
                WebUiElement.getInstance("non-existent", new By.ById("non-existent"));
        confirmed &= nonExistentElement.waitUntilVisible();
        return confirmed;
    }
}
