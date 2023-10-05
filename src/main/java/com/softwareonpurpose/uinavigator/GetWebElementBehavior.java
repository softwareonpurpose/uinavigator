package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;
    private final String css;
    protected final int ordinal;

    protected GetWebElementBehavior(String css, Integer ordinal) {
        this.css = css;
        this.ordinal = ordinal == null || ordinal < 0 ? 0 : ordinal;
        this.locator = new By.ByCssSelector(this.css);
    }

    protected static String composeCss(String locatorType, String locatorValue) {
        return String.format("%s%s", locatorType, locatorValue);
    }

    protected static String composeCss(String locatorType, String locatorValue, UiElement4 ancestor) {
        String ancestorCss = ancestor == null ? "" : ancestor.getCss();
        return String.format("%s %s", ancestorCss, composeCss(locatorType, locatorValue));
    }

    protected static String extractExceptionMessage(NoSuchElementException e) {
        String message = e.getMessage();
        int endIndex = message.indexOf("\n");
        return message.substring(0, endIndex);
    }

    abstract WebElement execute();

    public String getLocatorDescription() {
        return locator.toString();
    }

    public String getCss() {
        return css;
    }

    public boolean isByOrdinal() {
        return ordinal > 0;
    }
}
