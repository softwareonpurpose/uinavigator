package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.IsDisplayedBehavior;

public class WebIsDisplayedBehavior implements IsDisplayedBehavior {
    private final WebGetElementBehavior getBehavior;

    private WebIsDisplayedBehavior(WebGetElementBehavior getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static WebIsDisplayedBehavior getInstance(WebGetElementBehavior getBehavior) {
        return new WebIsDisplayedBehavior(getBehavior);
    }

    @Override
    public boolean execute() {
        return WebUiHost.getInstance().waitUntilVisible(getBehavior.execute());
    }
}
