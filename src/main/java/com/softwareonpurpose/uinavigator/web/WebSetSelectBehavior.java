package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.SetElementBehavior;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebSetSelectBehavior implements SetElementBehavior {
    private final WebGetElementBehavior getBehavior;

    private WebSetSelectBehavior(WebGetElementBehavior getElementBehavior) {
        this.getBehavior = getElementBehavior;
    }

    public static WebSetSelectBehavior getInstance(WebGetElementBehavior getElementBehavior) {
        return new WebSetSelectBehavior(getElementBehavior);
    }

    @Override
    public void execute(String value) {
        new Select((WebElement) getBehavior.execute()).selectByVisibleText(value);
    }
}
