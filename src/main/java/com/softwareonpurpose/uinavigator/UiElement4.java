package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiElement4 {
    private final By.ByCssSelector locator;
    @SuppressWarnings("FieldCanBeLocal")
    private final String description;

    private UiElement4(String description, String locatorType, String locatorValue, Integer ordinal) {
        this.description = description;
        String css = ordinal == null ? String.format("%s%s", locatorType, locatorValue) : String.format("%s%s:nth-of-type(%s)", locatorType, locatorValue, ordinal);
        locator = new By.ByCssSelector(css);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue) {
        return new UiElement4(description, locatorType, locatorValue, null);
    }

    public static UiElement4 getInstance(String description, String locatorType, String locatorValue, Integer ordinal) {
        return new UiElement4(description, locatorType, locatorValue, ordinal);
    }

    public boolean isDisplayed() {
        WebElement element = getElement();
        return element != null && element.isDisplayed();
    }

    public String getText() {
        return getElement().getText();
    }

    private WebElement getElement() {
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

    @Override
    public String toString() {
        return String.format("UiElement: %s", new Gson().toJson(this));
    }
}
