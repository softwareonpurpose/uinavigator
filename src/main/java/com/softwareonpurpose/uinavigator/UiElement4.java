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
        String cssSymbol = UiLocatorType4.ID.equals(locatorType) ? "#" : "";
        String css = ordinal == null ? String.format("%s%s", cssSymbol, locatorValue) : String.format("%s%s:nth-of-type(%s)", cssSymbol, locatorValue, ordinal);
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
        ChromeDriver driver = UiNavigator.getInstance().getDriver();
        return driver.findElement(locator);
    }
    
    @Override
    public String toString() {
        return String.format("UiElement: %s", new Gson().toJson(this));
    }
}
