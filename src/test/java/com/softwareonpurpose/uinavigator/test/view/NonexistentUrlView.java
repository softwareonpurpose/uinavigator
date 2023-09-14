package com.softwareonpurpose.uinavigator.test.view;

import com.softwareonpurpose.uinavigator.TestResources;
import com.softwareonpurpose.uinavigator.UiElement4;
import com.softwareonpurpose.uinavigator.UiLocatorType4;
import com.softwareonpurpose.uinavigator.UiView4;

public class NonexistentUrlView extends UiView4 {
    private static final TestResources resources = TestResources.getInstance();

    public NonexistentUrlView() {
        super(resources.getPageUrl("nonexistent"), UiElement4.getInstance("'Body' tag", UiLocatorType4.TAG, "body"));
    }

    public static NonexistentUrlView directNav() {
        new NonexistentUrlView().load();
        return UiView4.expect(NonexistentUrlView.class);
    }

    @Override
    protected boolean confirmState() {
        return true;
    }
}
