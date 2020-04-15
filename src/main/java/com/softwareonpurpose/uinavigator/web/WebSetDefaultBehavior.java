package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.SetElementBehavior;

public class WebSetDefaultBehavior implements SetElementBehavior {
    private final WebGetElementBehavior getBehavior;

    private WebSetDefaultBehavior(WebGetElementBehavior getElementBehavior) {
        getBehavior = getElementBehavior;
    }

    public static WebSetDefaultBehavior getInstance(WebGetElementBehavior getElementBehavior) {
        return new WebSetDefaultBehavior(getElementBehavior);
    }

    @Override
    public void execute(String value) {
        getBehavior.execute().clear();
        getBehavior.execute().sendKeys(value);
    }
}
