/**
 * Copyright 2018 Craig A. Stockton
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
     * Get the current Singleton instance of UiHost
     *
     * @return UiHost singleton instance constructed using current DriverInstantiation and Configuration
     */
    public static UiHost getInstance() {
        if (uiHost == null) {
            uiHost = new UiHost();
        }
        return uiHost;
    }

    /***
     * Construct a new UiHost instance using the provided DriverInstantiation.
     * The current Singleton UiHost will be stopped and replaced.
     * @param driverInstantiation DriverInstantiation with construction and configuration details
     * @return UiHost
     */
    public static UiHost getInstance(DriverInstantiation driverInstantiation) {
        setDriverInstantiation(driverInstantiation);
        quitInstance();
        uiHost = new UiHost();
        return uiHost;
    }

    /***
     * Configuration current configuration for UiHost instance
     * @return Configuration
     */
    public static Configuration getConfig() {
        if (config == null) {
            config = Configuration.getInstance();
        }
        return config;
    }

    /***
     * Quit the Singleton instance of UiHost
     */
    public static void quitInstance() {
        if (uiHost != null) {
            uiHost.quit();
            uiHost = null;
        }
    }

    /***
     * Details for instantiating a UiHost
     * @param driverInstantiation Instantiated DriverInstantiation containing details for UiHost instantiation
     */
    public static void setDriverInstantiation(DriverInstantiation driverInstantiation) {
        UiHost.driverInstantiation = driverInstantiation;
    }

    /**
     * Navigate UiHost (browser) to the provided URI.
     *
     * @param uri String URI
     */
    public void load(String uri) {
        logger.info(String.format("Navigate browser to %s", uri));
        driver.get(uri);
    }

    /***
     * Execute the provided JavaScript in the UiHost
     * @param javaScript Script to be executed
     */
    public void execute(String javaScript) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(javaScript);
        }
    }

    /***
     * Set the value of the identified attribute to the identified UiElement
     * @param locatorType One of the UiElement.LocatorTypes
     * @param locatorValue The appropriate value of the locator type for the desired element
     * @param attribute An attribute of the UiElement
     * @param value A value to which the attribute is to be set
     */
    void setAttribute(String locatorType, String locatorValue, String attribute, String value) {
        WebElement element = findUiElement(constructLocator(locatorType, locatorValue));
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attribute, value);
        }
    }

    /**
     * The current url of the UiHost
     *
     * @return String which is the current URI of the browser
     */
    public String getUri() {
        return driver.getCurrentUrl();
    }

    /**
     * Find the element located using the provided UiElement.LocatorType and value
     *
     * @param locatorType  One of the UiElement.LocatorType values
     * @param locatorValue the appropriate value for the identified element
     * @return A List of WebElements within the current web page
     */
    Object findUiElement(String locatorType, String locatorValue) {
        By locator = constructLocator(locatorType, locatorValue);
        return findUiElement(locator);
    }

    private WebElement findUiElement(By locator) {
        List<WebElement> elements = findUiElements(locator);
        if (elements != null && elements.size() > 0) {
            return elements.get(0);
        } else {
            logger.warn(String.format("WARNING: Unable to find any element using locator '%s'", locator.toString()));
        }
        return null;
    }

    /***
     * Select frame within UiHost
     * @param frameId
     */
    public void selectFrame(String frameId) {
        driver.switchTo().frame(frameId);
    }

    /***
     * Select UiHost window
     */
    public void selectWindow() {
        driver.switchTo().defaultContent();
    }

    /**
     * Find all elements located using the provided UiElement.LocatorType and value
     *
     * @param locatorType  One of the UiElement.LocatorType values
     * @param locatorValue The appropriate value for the identified element
     * @return A List of WebElements within the current web page
     */
    List<Object> findUiElements(String locatorType, String locatorValue) {
        By locator = constructLocator(locatorType, locatorValue);
        return new ArrayList<>(Objects.requireNonNull(findUiElements(locator)));
    }

    private List<WebElement> findUiElements(By locator) {
        List<WebElement> elements;
        try {
            elements = driver.findElements(locator);
        } catch (WebDriverException e) {
            logger.warn(String.format("WARNING: Unable to find any element using locator '%s'", locator.toString()));
            return null;
        }
        return elements;
    }

    /***
     * Wait until the identified element is visible
     * @param locatorType One of the values in UiElement.LocatorType
     * @param locatorValue The appropriate value for the identified element
     * @return boolean Indicates whether the element was found
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
     * @return String The name of the current UiHost driver
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
