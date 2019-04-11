/*
  Copyright 2018 Craig A. Stockton
  <p/>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p/>
  http://www.apache.org/licenses/LICENSE-2.0
  <p/>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.driver.DefaultChromeInstantiation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Host for application UI
 */
@SuppressWarnings("WeakerAccess")
public class UiHost {
    private static UiHost uiHost;
    private static Configuration config;
    private static DriverInstantiation driverInstantiation;
    private final Logger logger = LoggerFactory.getLogger("");
    private WebDriver driver;

    private UiHost() {
        if (driverInstantiation == null) {
            setDriverInstantiation(DefaultChromeInstantiation.getInstance());
        }
        instantiateUiDriver();
    }

    /**
     * Get instance of UiHost
     *
     * @return UiHost instance
     */
    public static UiHost getInstance() {
        if (uiHost == null) {
            uiHost = new UiHost();
        }
        return uiHost;
    }

    /***
     * Get UiHost instance
     *
     * @param driverInstantiation DriverInstantiation
     * @return UiHost instance
     */
    public static UiHost getInstance(DriverInstantiation driverInstantiation) {
        setDriverInstantiation(driverInstantiation);
        quitInstance();
        uiHost = new UiHost();
        return uiHost;
    }

    /***
     * Configuration of UiHost
     *
     * @return Configuration for instantiating UiHosts
     */
    public static Configuration getConfig() {
        if (config == null) {
            config = Configuration.getInstance();
        }
        return config;
    }

    /***
     * Quit UiHost
     */
    public static void quitInstance() {
        if (uiHost != null) {
            uiHost.quit();
            uiHost = null;
        }
    }

    /***
     * Set UI host instantiation details
     *
     * @param driverInstantiation DriverInstantiation details
     */
    public static void setDriverInstantiation(DriverInstantiation driverInstantiation) {
        UiHost.driverInstantiation = driverInstantiation;
    }

    /**
     * Navigate UI host to URI
     *
     * @param uri String URI
     */
    public void load(String uri) {
        logger.info(String.format("Navigate browser to %s", uri));
        driver.get(uri);
    }

    /***
     * Execute JavaScript
     *
     * @param javaScript String to execute
     */
    @SuppressWarnings("unused")
    public void execute(String javaScript) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(javaScript);
        }
    }

    /***
     * Locate UI element and set attribute to value
     *
     * @param locatorType String UiElement.LocatorType
     * @param locatorValue String value of locator type
     * @param attribute String attribute
     * @param value String attribute value
     */
    void setAttribute(String locatorType, String locatorValue, String attribute, String value) {
        WebElement element = findUiElement(constructLocator(locatorType, locatorValue));
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attribute, value);
        }
    }

    /**
     * Current URI of UI host
     *
     * @return String URI
     */
    public String getUri() {
        return driver.getCurrentUrl();
    }

    /**
     * Find and return UI element by locator type and value
     *
     * @param locatorType  String UiElement.LocatorType
     * @param locatorValue String value
     * @return WebElement (Selenium)
     */
    WebElement findUiElement(String locatorType, String locatorValue) {
        By locator = constructLocator(locatorType, locatorValue);
        return findUiElement(locator);
    }

    private WebElement findUiElement(By locator) {
        List<WebElement> elements = findUiElements(locator);
        if (elements != null && elements.size() > 0) {
            return elements.get(0);
        } else {
            logger.warn(String.format("WARNING: Unable to find any element %s", locator.toString()));
        }
        return null;
    }

    /***
     * Switch driver to default UI window
     */
    void selectWindow() {
        driver.switchTo().defaultContent();
    }

    /**
     * Find and return UI elements by locator type and value
     *
     * @param locatorType  String UiElement.LocatorType
     * @param locatorValue String value
     * @return WebElement collection (Selenium)
     */
    List<WebElement> findUiElements(String locatorType, String locatorValue) {
        By locator = constructLocator(locatorType, locatorValue);
        return new ArrayList<>(Objects.requireNonNull(findUiElements(locator)));
    }

    private List<WebElement> findUiElements(By locator) {
        List<WebElement> elements;
        try {
            elements = driver.findElements(locator);
        } catch (WebDriverException e) {
            logger.warn(String.format("WARNING: Unable to find any element %s", locator.toString()));
            return null;
        }
        return elements;
    }

    /***
     * 'Visible' state
     *
     * @param locatorType String UiElement.LocatorType
     * @param locatorValue String value
     * @return boolean is visible
     */
    boolean waitUntilVisible(String locatorType, String locatorValue) {
        By locator = constructLocator(locatorType, locatorValue);
        try {
            new WebDriverWait(driver, getConfig().getTimeout())
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            String warningMessageFormat = "WARNING: UiElement '%s' failed to be displayed within %d seconds";
            logger.warn(String.format(warningMessageFormat, locator.toString(), getConfig().getTimeout()));
            return false;
        }
        return true;
    }

    private By constructLocator(String locatorType, String locatorValue) {
        By locator;
        switch (locatorType) {
            case UiElement.LocatorType.ID:
                locator = By.id(locatorValue);
                break;
            case UiElement.LocatorType.NAME:
                locator = By.name(locatorValue);
                break;
            case UiElement.LocatorType.CLASS:
                locator = By.className(locatorValue);
                break;
            case UiElement.LocatorType.TAG:
                locator = By.tagName(locatorValue);
                break;
            default:
                locator = By.className(locatorValue);
                break;
        }
        return locator;
    }

    /**
     * Get name of UI host driver
     *
     * @return String name of driver
     */
    String getDriverName() {
        return driver.getClass().getName();
    }

    private void quit() {
        driver.quit();
        driver = null;
    }

    private void instantiateUiDriver() {
        logger.info("Launch browser");
        driver = driverInstantiation.execute();
    }
}
