package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UiElement4 {
    private static Logger logger;
    private final String description;
    private final GetWebElementBehavior getElementBehavior;
    
    private UiElement4(String description, String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        this.description = description;
        getElementBehavior = switch (locatorType) {
            case UiLocatorType4.ID -> GetElementWithId.getInstance(locatorValue);
            case UiLocatorType4.CLASS -> GetElementWithClass.getInstance(locatorValue, ordinal, parent);
            case UiLocatorType4.TAG -> GetElementWithTag.getInstance(locatorValue, ordinal, parent);
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
    
    public String getHref() {
        return getAttribute("href");
    }
    
    public String getAttribute(String attribute) {
        WebElement element = getElement();
        return element == null ? null : element.getAttribute(attribute);
    }
    
    public String getStyleProperty(String property) {
        WebElement element = getElement();
        return element == null ? null : element.getCssValue(property);
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
    
    public String getDescription() {
        return description;
    }
    
    public boolean isLocatedByClass() {
        return GetElementWithClass.class.equals(getElementBehavior.getClass());
    }
    
    protected WebElement getElement() {
        return getElementBehavior.execute();
    }
    
    String getCss() {
        return getElementBehavior.getCss();
    }
    
    private By.ByCssSelector getLocator() {
        return getElementBehavior.getLocator();
    }
    
    private Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getLogger("");
        }
        return logger;
    }
    
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return String.format("UiElement: %s", gson.toJson(this));
    }
}
