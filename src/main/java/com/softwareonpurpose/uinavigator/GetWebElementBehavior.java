package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    protected final UiElement4 parent;
    private final String css;

    protected GetWebElementBehavior(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        this.css = composeCss(locatorType, locatorValue, ordinal);
        locator = new By.ByCssSelector(css);
        this.parent = parent;
    }

    abstract WebElement execute();

    public String getCss() {
        return css;
    }

    public By.ByCssSelector getLocator() {
        return locator;
    }

    protected boolean isParentLocatedByClass() {
        return parent == null || parent.isLocatedByClass();
    }

    protected WebElement getParent() {
        return parent.getElement();
    }

    protected boolean hasParent() {
        return parent != null;
    }

    protected abstract String composeCss(String locatorType, String locatorValue, Integer ordinal);

    protected String getParentCss() {
        return parent.getCss();
    }
}
