package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UiElement4 {
    private final By.ByCssSelector locator;
    @SuppressWarnings("FieldCanBeLocal")
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
        return UiNavigator.getInstance().getDriver().findElement(locator);
    }

    @Override
    public String toString() {
        return String.format("UiElement: %s", new Gson().toJson(this));
    }
}
