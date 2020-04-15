package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetTextBehavior;
import org.openqa.selenium.WebElement;

public class WebGetTextDefaultBehavior implements GetTextBehavior {
    private final WebGetElementBehavior getBehavior;

    private WebGetTextDefaultBehavior(WebGetElementBehavior getElementBehavior) {
        this.getBehavior = getElementBehavior;
    }

    public static WebGetTextDefaultBehavior getInstance(WebGetElementBehavior getElementBehavior) {
        return new WebGetTextDefaultBehavior(getElementBehavior);
    }

    @Override
    public String execute() {
        final WebElement element = getBehavior.execute();
        if (element == null) {
            return null;
        }
        boolean isInputElement = element.getTagName().equals("input");
        return isInputElement ? element.getAttribute("value") : element.getText();
    }
}
