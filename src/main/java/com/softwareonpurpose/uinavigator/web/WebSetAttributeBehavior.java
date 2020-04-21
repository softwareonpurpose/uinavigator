package com.softwareonpurpose.uinavigator.web;

public class WebSetAttributeBehavior {
    private final WebGetElementBehavior getBehavior;

    private WebSetAttributeBehavior(WebGetElementBehavior getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static WebSetAttributeBehavior getInstance(WebGetElementBehavior getBehavior) {
        return new WebSetAttributeBehavior(getBehavior);
    }

    public void execute(String attribute, String value) {
        WebUiHost.getInstance().setAttribute(getBehavior.execute(), attribute, value);
    }
}
