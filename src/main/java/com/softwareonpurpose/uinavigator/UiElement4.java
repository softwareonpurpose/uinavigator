package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import com.softwareonpurpose.uinavigator.behavior.getelement.GetElementDirectly;
import com.softwareonpurpose.uinavigator.behavior.getelement.GetElementFromList;
import com.softwareonpurpose.uinavigator.behavior.getelement.GetWebElementBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiElement4 {
    private static Logger logger;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String description;
    private final String css;
    private final GetWebElementBehavior getElementBehavior;

    private UiElement4(String description, String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        this.description = description;
        css = composeCss(locatorType, locatorValue, ordinal, parent);
        getElementBehavior = UiLocatorType4.CLASS.equals(locatorType) && ordinal != null ? GetElementFromList.getInstance() : GetElementDirectly.getInstance();
    }

    private static String composeCss(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        String thisCss = String.format("%s%s", locatorType, locatorValue);
        thisCss += ordinal == null ? "" : String.format(":nth-of-type(%s)", ordinal);
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
        By locator = getLocator();
        ChromeDriver driver = UiNavigator.getInstance().getDriver();
        WebElement element;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            getLogger().warn(String.format("Element NOT FOUND using %s", locator));
            element = null;
        }
        return element;
    }

    private By.ByCssSelector getLocator() {
        return new By.ByCssSelector(css);
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
