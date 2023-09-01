package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class UiElement4 {
    private final By.ByCssSelector locator;
    @SuppressWarnings("FieldCanBeLocal")
    private final String description;
    private final int ordinal;

    private UiElement4(String description, String locatorType, String locatorValue, Integer ordinal) {
        this.description = description;
        String cssSymbol = UiLocatorType4.ID.equals(locatorType) ? "#" : "";
        this.ordinal = ordinal == null ? 1 : ordinal;
        String css = String.format("%s%s", cssSymbol, locatorValue);
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
        WebElement element = getElement();
        return element == null ? null : getElement().getText();
    }
    
    private WebElement getElement() {
        int index = ordinal - 1;
        ChromeDriver driver = UiNavigator.getInstance().getDriver();
        if (ordinal == 1) {
            return driver.findElement(locator);
        } else {
            List<WebElement> elements = driver.findElements(locator);
            return ordinal <= elements.size() ? elements.get(index) : null;
        }
    }

    @Override
    public String toString() {
        return String.format("UiElement: %s", new Gson().toJson(this));
    }
}
