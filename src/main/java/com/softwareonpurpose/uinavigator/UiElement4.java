package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiElement4 {
    private final By.ByCssSelector locator;
    private final ChromeDriver driver = UiNavigator.getInstance().getDriver();
    private final String description;
    
    private UiElement4(String description, String locatorType, String locatorValue) {
        this.description = description;
        String cssSymbol = UiLocatorType4.ID.equals(locatorType) ? "#" : "";
        String css = String.format("%s%s", cssSymbol, locatorValue);
        locator = new By.ByCssSelector(css);
    }
    
    public static UiElement4 getInstance(String description, String locatorType, String locatorValue) {
        return new UiElement4(description, locatorType, locatorValue);
    }
    
    public boolean isDisplayed() {
        return getElement().isDisplayed();
    }
    
    private WebElement getElement() {
        return driver.findElement(locator);
    }
}
