package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UiNavigator {
    private static ChromeDriver driver = new ChromeDriver(new ChromeOptions());

    public static void quitInstance() {
        driver.quit();
        driver = null;
    }

    public static void load(String url) {
        driver.get(url);
    }

    public static WebElement getElement(String description, String locatorType, String locatorValue) {
        String cssSymbol = "#";
        String css = String.format("%s%s", cssSymbol, locatorValue);
        By locator = new By.ByCssSelector(css);
        return driver.findElement(locator);
    }
}
