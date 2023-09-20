package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    private final String css;

    protected GetWebElementBehavior(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        this.css = composeCss(locatorType, locatorValue, ordinal, parent);
        locator = new By.ByCssSelector(css);
    }

    abstract WebElement execute();

    protected String composeCss(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        String thisCss = String.format("%s%s", locatorType, locatorValue);
        thisCss += ordinal == null ? "" : String.format(":nth-of-type(%s)", ordinal);
        String parentCss = parent == null ? "" : String.format("%s ", parent.getCss());
        return String.format("%s%s", parentCss, thisCss);
    }

    public String getCss() {
        return css;
    }

    public By.ByCssSelector getLocator() {
        return locator;
    }
}
