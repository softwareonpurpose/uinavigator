package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WebUiDriver implements UiDriver {
    private final WebDriver driver;

    public WebUiDriver() {
        driver = (WebDriver) UiDriverService.getInstance().getDriver();
    }

    @Override
    public void load(String address) {
        driver.get(address);
    }

    @Override
    public void executeScript(String script, Object[] args) {
        try {
            ((JavascriptExecutor) driver).executeScript(script, args);
        } catch (JavascriptException e) {
            LoggerFactory.getLogger(this.getClass()).warn(String.format("Unable to execute javascript: [%s]", script));
        }
    }

    @Override
    public List<Object> findElements(Object locator) {
        List<Object> elements = new ArrayList<>();
        try {
            elements.addAll(driver.findElements((By) locator));
        } catch (WebDriverException e) {
            LoggerFactory.getLogger(this.getClass()).warn(String.format("WARNING: Unable to find any element %s", locator.toString()));
        }
        return elements;
    }

    @Override
    public boolean waitUntilVisible(UiElementGet getElement) {
        final long timeout = UiNavigatorConfiguration.getInstance().getTimeout();
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOf((WebElement) getElement.execute()));
        } catch (WebDriverException | NullPointerException e) {
            String warningMessageFormat = "WARNING: WebElement [%s] failed to be displayed within %d seconds";
            final String message = String.format(warningMessageFormat, getElement.toString(), timeout);
            LoggerFactory.getLogger(this.getClass()).warn(message);
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return driver.getClass().getSimpleName();
    }

    @Override
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public String getState(String[] identifiers) {
        return CookieViewer.getInstance(driver).getCookieValue(identifiers[0], identifiers[1], identifiers[2]);
    }

    @Override
    public void switchTo(UiElement element) {
        WebElement webElement = (WebElement) element.getLocator().execute();
        final WebDriver.TargetLocator switchTo = driver.switchTo();
        if ("iframe".equals(webElement.getTagName())) {
            switchTo.frame(webElement);
        } else {
            switchTo.defaultContent();
        }
    }

    @Override
    public Object findElement(Object locator) {
        List<Object> elements = findElements(locator);
        if (elements.size() == 0) {
            final String message = String.format("WARNING: Unable to find any element %s", locator.toString());
            LoggerFactory.getLogger(this.getClass()).warn(message);
            return null;
        } else {
            return elements.get(0);
        }
    }

    @Override
    public String getAddress() {
        return driver.getCurrentUrl();
    }
}
