package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UiElement4 {
    private static Logger logger;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String description;
    private final String css;
    private transient final GetWebElementBehavior getElementBehavior;
    private final By.ByCssSelector locator;

    private UiElement4(String description, String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        this.description = description;
        css = composeCss(locatorType, locatorValue, ordinal, parent);
        locator = new By.ByCssSelector(css);
        getElementBehavior = UiLocatorType4.CLASS.equals(locatorType) && (ordinal != null && ordinal > 0)
                ? GetElementFromList.getInstance(locator)
                : GetElementDirectly.getInstance(locator);
    }

    private static String composeCss(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        String thisCss = String.format("%s%s", locatorType, locatorValue);
        thisCss += ordinal == null || UiLocatorType4.CLASS.equals(locatorType)  ? "" : String.format(":nth-of-type(%s)", ordinal);
        String parentCss = parent == null ? "" : String.format("%s ", parent.getCss());
        return String.format("%s%s", parentCss, thisCss);
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
        return locator;
    }

    private String getCss() {
        return css;
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
