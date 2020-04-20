package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.WebElement;

public class WebGetAttributeBehavior implements GetAttributeBehavior{
    private final WebGetElementBehavior getBehavior;

    private WebGetAttributeBehavior(WebGetElementBehavior getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static WebGetAttributeBehavior getInstance(WebGetElementBehavior getBehavior) {
        return new WebGetAttributeBehavior(getBehavior);
    }

    @Override
    public String execute(String attribute) {
        WebElement element = getBehavior.execute();
        return element == null ? null : attribute == null ? null : element.getAttribute(attribute);
    }
}
