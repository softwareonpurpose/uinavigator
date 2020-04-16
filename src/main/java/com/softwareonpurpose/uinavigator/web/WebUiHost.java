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
package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiNavigatorConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Host for application UI
 */
@SuppressWarnings("WeakerAccess")
public class WebUiHost implements UiHost {
    private static WebUiHost webUiHost;
    private static UiNavigatorConfiguration config;
    private static DriverInstantiation driverInstantiation;
    private final Logger logger = LoggerFactory.getLogger("");
    private WebDriver driver;

    private WebUiHost() {
        if (driverInstantiation == null) {
            setDriverInstantiation(DefaultChromeInstantiation.getInstance());
        }
        instantiateUiDriver();
    }

    /**
     * Get instance of WebUiHost
     *
     * @return WebUiHost instance
     */
    public static WebUiHost getInstance() {
        if (webUiHost == null) {
            webUiHost = new WebUiHost();
        }
        return webUiHost;
    }

    /***
     * Get WebUiHost instance
     *
     * @param driverInstantiation DriverInstantiation
     * @return WebUiHost instance
     */
    public static WebUiHost getInstance(DriverInstantiation driverInstantiation) {
        setDriverInstantiation(driverInstantiation);
        quitInstance();
        webUiHost = new WebUiHost();
        return webUiHost;
    }

    /***
     * UiNavigatorConfiguration of WebUiHost
     *
     * @return UiNavigatorConfiguration for instantiating UiHosts
     */
    public static UiNavigatorConfiguration getConfig() {
        if (config == null) {
            config = UiNavigatorConfiguration.getInstance();
        }
        return config;
    }

    /***
     * Quit WebUiHost
     */
    public static void quitInstance() {
        if (webUiHost != null) {
            webUiHost.quit();
            webUiHost = null;
        }
    }

    /***
     * Set UI host instantiation details
     *
     * @param driverInstantiation DriverInstantiation details
     */
    public static void setDriverInstantiation(DriverInstantiation driverInstantiation) {
        WebUiHost.driverInstantiation = driverInstantiation;
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

    void setAttribute(WebElement element, String attribute, String value) {
        if (driver instanceof JavascriptExecutor && element != null) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attribute, value);
        } else {
            logger.warn(String.format("Unable to set '%s' attribute of element to \"%s\"", attribute, value));
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

    WebElement findUiElement(By locator) {
        List<WebElement> elements = findUiElements(locator);
        //noinspection ConstantConditions   the elements list could return with zero items
        if (elements == null || elements.size() == 0) {
            logger.warn(String.format("WARNING: Unable to find any element %s", locator.toString()));
            return null;
        } else {
            return elements.get(0);
        }
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

    boolean waitUntilVisible(WebElement element) {
        try {
            new WebDriverWait(driver, getConfig().getTimeout())
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            String warningMessageFormat = "WARNING: WebElement failed to be displayed within %d seconds";
            logger.warn(String.format(warningMessageFormat, getConfig().getTimeout()));
            return false;
        }
        return true;
    }

    private By constructLocator(String locatorType, String locatorValue) {
        By locator;
        switch (locatorType) {
            case UiLocatorType.ID:
                locator = By.id(locatorValue);
                break;
            case UiLocatorType.NAME:
                locator = By.name(locatorValue);
                break;
            case UiLocatorType.TAG:
                locator = By.tagName(locatorValue);
                break;
            case UiLocatorType.CLASS:
            default:
                locator = By.className(locatorValue);
        }
        return locator;
    }

    /**
     * Get name of UI host driver
     *
     * @return String name of driver
     */
    public String getDriverName() {
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

    /**
     * Get the value of a specific cookie identified by name, domain and path
     *
     * @param name   Name
     * @param domain Domain
     * @param path   Path
     * @return String value of the identified cookie
     */
    public String getCookieValue(String name, String domain, String path) {
        return CookieViewer.getInstance(driver).getCookieValue(name, domain, path);
    }
}
