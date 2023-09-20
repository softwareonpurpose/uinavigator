package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    private final String css;

    protected GetWebElementBehavior(String locatorType, String locatorValue, Integer ordinal, String parentCss) {
        this.css = composeCss(locatorType, locatorValue, ordinal, parentCss);
        locator = new By.ByCssSelector(css);
    }

    abstract WebElement execute();

    abstract protected String composeCss(String locatorType, String locatorValue, Integer ordinal, String parentCss);

    public String getCss() {
        return css;
    }

    public By.ByCssSelector getLocator() {
        return locator;
    }
}
