package com.softwareonpurpose.uinavigator.test.view;

import com.softwareonpurpose.uinavigator.TestResources;
import com.softwareonpurpose.uinavigator.UiElement4;
import com.softwareonpurpose.uinavigator.UiLocatorType4;
import com.softwareonpurpose.uinavigator.UiView4;

public class BasicView extends UiView4 {

    public BasicView() {
        super(TestResources.getInstance().getPageUrl("basic"), UiElement4.getInstance("'Basic' view", UiLocatorType4.TAG, "body"));
    }

    public static BasicView expect() {
        return UiView4.expect(BasicView.class);
    }

    public static BasicView directNav() {
        new BasicView().load();
        return BasicView.expect();
    }

    @Override
    protected boolean confirmState() {
        return this.getElement().isDisplayed();
    }
}
