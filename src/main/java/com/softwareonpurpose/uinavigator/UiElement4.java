package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiElement4 {
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String description;
    private final String css;
    private final UiElement4 parent;

    private UiElement4(String description, String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        this.description = description;
        css = composeCss(locatorType, locatorValue, ordinal);
        this.parent = parent;
    }

    private static String composeCss(String locatorType, String locatorValue, Integer ordinal) {
        String css = String.format("%s%s", locatorType, locatorValue);
        css += ordinal == null ? "" : String.format(":nth-of-type(%s)", ordinal);
        return css;
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue) {
        return new UiElement4(description, locatorType, locatorValue, null, null);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, Integer ordinal) {
        return new UiElement4(description, locatorType, locatorValue, ordinal, null);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, UiElement4 parent) {
        return new UiElement4(description, locatorType, locatorValue, null, parent);
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
        By locator = new By.ByCssSelector(getCss());
        ChromeDriver driver = UiNavigator.getInstance().getDriver();
        WebElement element;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            //  TODO:  Log 'Warning' here -- "Element NOT FOUND using locator [locator info]"
            element = null;
        }
        return element;
    }

    private String getCss() {
        String parentCss = parent == null ? "" : String.format("%s ", parent.getCss());
        return String.format("%s%s", parentCss, css);
    }

    @Override
    public String toString() {
        return String.format("UiElement: %s", new Gson().toJson(this));
    }

    public String getHref() {
        return getAttribute("href");
    }

    public void click() {
        WebElement element = getElement();
        //noinspection StatementWithEmptyBody
        if (element != null) {
            element.click();
        } else {
            //  TODO:  Log 'Warning' of inability to click the element
        }
    }

    public String getAttribute(String attribute) {
        WebElement element = getElement();
        return element == null ? null : element.getAttribute(attribute);
    }
}
