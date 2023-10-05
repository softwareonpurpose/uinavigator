package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class UiElement4 {
    private static Logger logger;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String description;
    private final GetWebElementBehavior getElementBehavior;

    private UiElement4(String description, GetWebElementBehavior getElementBehavior) {
        this.description = description;
        this.getElementBehavior = getElementBehavior;
    }

    private static String composeCss(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        String thisCss = String.format("%s%s", locatorType, locatorValue);
        thisCss += ordinal == null || UiLocatorType4.CLASS.equals(locatorType) ? "" : String.format(":nth-of-type(%s)", ordinal);
        String parentCss = parent == null ? "" : String.format("%s ", parent.getCss());
        return String.format("%s%s", parentCss, thisCss);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue) {
        GetWebElementBehavior getElementBehavior = GetByCssFromRoot.getInstance(locatorType, locatorValue);
        return new UiElement4(description, getElementBehavior);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, int ordinal) {
        GetWebElementBehavior getElementBehavior = GetByCssOrdinalFromRoot.getInstance(locatorType, locatorValue, ordinal);
        return new UiElement4(description, getElementBehavior);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, UiElement4 ancestor) {
        GetWebElementBehavior getElementBehavior = ancestor.isByOrdinal()
                ? GetByCssFromAncestorOrdinal.getInstance(locatorType, locatorValue, ancestor)
                : GetByCssFromAncestor.getInstance(locatorType, locatorValue, ancestor);
        return new UiElement4(description, getElementBehavior);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, int ordinal, UiElement4 ancestor) {
        GetWebElementBehavior getElementBehavior = ancestor.isByOrdinal()
                ? GetByCssOrdinalFromAncestorOrdinal.getInstance(locatorType, locatorValue, ordinal, ancestor)
                : GetByCssOrdinalFromAncestor.getInstance(locatorType, locatorValue, ordinal, ancestor);
        return new UiElement4(description, getElementBehavior);
    }

    private boolean isByOrdinal() {
        return getElementBehavior.isByOrdinal();
    }

    public boolean isDisplayed() {
        WebElement element = getElement();
        return element != null && element.isDisplayed();
    }

    public String getText() {
        WebElement element = getElement();
        return element == null ? null : element.getText();
    }

    protected WebElement getElement() {
        return getElementBehavior == null ? null : getElementBehavior.execute();
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
            getLogger().warn(String.format("%s NOT FOUND using %s", description, getElementBehavior.getLocatorDescription()));
        } else {
            try {
                element.click();
            } catch (Exception e) {
                getLogger().warn(String.format("UNABLE TO CLICK %s using %s", description, getElementBehavior.getLocatorDescription()));
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
