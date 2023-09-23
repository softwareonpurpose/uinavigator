package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    protected final UiElement4 parent;
    protected final int ordinal;
    private final String css;

    protected GetWebElementBehavior(String locatorValue, Integer ordinal, UiElement4 parent) {
        this.parent = parent;
        this.ordinal = ordinal == null || ordinal < 1 ? 1 : ordinal;
        this.css = composeCss(locatorValue);
        locator = new By.ByCssSelector(css);
    }

    abstract WebElement execute();

    public String getCss() {
        return css;
    }

    public By.ByCssSelector getLocator() {
        return locator;
    }

    protected boolean isParentLocatedByClass() {
        return parent != null && parent.isLocatedByClass();
    }

    protected WebElement getParent() {
        return parent.getElement();
    }

    protected boolean hasParent() {
        return parent != null;
    }

    protected abstract String composeCss(String locatorValue);

    protected String getParentCss() {
        return parent == null ? "" : parent.getCss();
    }
}
