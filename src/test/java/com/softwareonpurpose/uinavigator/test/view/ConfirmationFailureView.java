package com.softwareonpurpose.uinavigator.test.view;

import com.softwareonpurpose.uinavigator.TestResources;
import com.softwareonpurpose.uinavigator.UiElement4;
import com.softwareonpurpose.uinavigator.UiLocatorType4;
import com.softwareonpurpose.uinavigator.UiView4;

public class ConfirmationFailureView extends UiView4 {
    public ConfirmationFailureView() {
        super(TestResources.getInstance().getPageUrl("basic"), UiElement4.getInstance("'View' element", UiLocatorType4.TAG, "body"));
    }

    public static ConfirmationFailureView directNav() {
        new ConfirmationFailureView().load();
        return ConfirmationFailureView.expect();
    }

    private static ConfirmationFailureView expect() {
        return UiView4.expect(ConfirmationFailureView.class);
    }

    @Override
    protected boolean confirmState() {
        return false;
    }
}
