package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UiElement4 {
    private transient static Logger logger;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String description;
    private final transient GetWebElementBehavior getElementBehavior;

    private UiElement4(String description, String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        this.description = description;
        String parentCss = parent == null ? "" : String.format("%s ", parent.getCss());
        getElementBehavior = switch (locatorType) {
            case UiLocatorType4.ID -> GetElementWithId.getInstance(locatorValue);
            case UiLocatorType4.TAG -> GetElementWithTag.getInstance(locatorValue, ordinal, parentCss);
            default -> null;
        };
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue) {
        return new UiElement4(description, locatorType, locatorValue, null, null);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, int ordinal) {
        return new UiElement4(description, locatorType, locatorValue, ordinal, null);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, UiElement4 parent) {
        return new UiElement4(description, locatorType, locatorValue, null, parent);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, int ordinal, UiElement4 parent) {
        return new UiElement4(description, locatorType, locatorValue, ordinal, parent);
    }

    public boolean isDisplayed() {
        WebElement element = getElement();
        return element != null && element.isDisplayed();
    }

    public String getText() {
        WebElement element = getElement();
        return element == null ? null : element.getText();
    }

    private WebElement getElement() {
        return getElementBehavior.execute();
    }

    private By.ByCssSelector getLocator() {
        return getElementBehavior.getLocator();
    }

    String getCss() {
        return getElementBehavior.getCss();
    }

    @Override
    public String toString() {
        return String.format("UiElement: %s", new Gson().toJson(this));
    }

    public String getHref() {
        return getAttribute("href");
    }

    public void click() {
        getLogger().info(String.format("Click %s ...", description));
        WebElement element = getElement();
        if (element == null) {
            getLogger().warn(String.format("%s NOT FOUND using %s", description, getLocator()));
        } else {
            try {
                element.click();
            } catch (Exception e) {
                getLogger().warn(String.format("UNABLE TO CLICK %s using %s", description, getLocator()));
            }
        }
    }

    private Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getLogger("");
        }
        return logger;
    }

    public String getAttribute(String attribute) {
        WebElement element = getElement();
        return element == null ? null : element.getAttribute(attribute);
    }

    public String getStyleProperty(String property) {
        WebElement element = getElement();
        return element == null ? null : element.getCssValue(property);
    }

    public String getDescription() {
        return description;
    }
}
