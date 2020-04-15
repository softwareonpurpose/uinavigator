package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetElementBehavior;
import com.softwareonpurpose.uinavigator.GetTextBehavior;
import org.openqa.selenium.support.ui.Select;

public class WebGetTextSelectBehavior implements GetTextBehavior {
    private final WebGetElementBehavior getBehavior;

    private WebGetTextSelectBehavior(WebGetElementBehavior getElementBehavior) {
        this.getBehavior = getElementBehavior;
    }

    public static WebGetTextSelectBehavior getInstance(WebGetElementBehavior getElementBehavior) {
        return new WebGetTextSelectBehavior(getElementBehavior);
    }

    @Override
    public String execute() {
        Select select = new Select(getBehavior.execute());
        return select.getOptions().size() == 0 ? null : select.getFirstSelectedOption().getText();
    }
}
