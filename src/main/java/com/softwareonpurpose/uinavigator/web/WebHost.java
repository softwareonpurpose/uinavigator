package com.softwareonpurpose.uinavigator.web;
/*
  Copyright 2020 Craig A. Stockton
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

import com.softwareonpurpose.uinavigator.UiDriverBehaviors;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverInstantiation;
import com.softwareonpurpose.uinavigator.UiNavigatorConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Host for application UI
 */
@SuppressWarnings("WeakerAccess")
public class WebHost {
    private static WebHost webHost;
    private static UiNavigatorConfiguration config;
    private static UiDriverInstantiation driverInstantiation;
    private final Logger logger = LoggerFactory.getLogger("");
    private final UiDriverBehaviors behaviors;
    private WebDriver driver;

    private WebHost() {
        UiDriverBehaviors.getInstance();
        if (driverInstantiation == null) {
            setDriverInstantiation(ChromeDriverInstantiation.getInstance());
        }
        instantiateUiDriver();
        behaviors = UiDriverBehaviors.getInstance();
    }

    /**
     * Get instance of WebUiHost
     *
     * @return WebUiHost instance
     */
    public static WebHost getInstance() {
        if (driverInstantiation == null) {
            setDriverInstantiation(ChromeDriverInstantiation.getInstance());
        }
        if (webHost == null) {
            webHost = new WebHost();
        }
        return webHost;
    }

    /***
     * Get WebUiHost instance
     *
     * @param driverInstantiation DriverInstantiation
     * @return WebUiHost instance
     */
    public static WebHost getInstance(UiDriverInstantiation driverInstantiation) {
        setDriverInstantiation(driverInstantiation);
        quitInstance();
        webHost = new WebHost();
        return webHost;
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
        UiDriverGet.quit();
        if (webHost != null) {
            webHost.quit();
            webHost = null;
        }
    }

    /***
     * Set UI host instantiation details
     *
     * @param driverInstantiation DriverInstantiation details
     */
    public static void setDriverInstantiation(UiDriverInstantiation driverInstantiation) {
        WebHost.driverInstantiation = driverInstantiation;
        UiDriverGet.setBehaviors(driverInstantiation, WebDriverQuit.getInstance());
    }

    /**
     * Navigate UI host to URI
     *
     * @param address String URI
     */
    public void load(String address) {
        logger.info(String.format("Navigate browser to %s", address));
        behaviors.load(address);
    }

    /***
     * Execute JavaScript
     *
     * @param javaScript String to execute
     */
    @SuppressWarnings("unused")
    public void execute(String javaScript, Object... args) {
        if (driver instanceof JavascriptExecutor) {
            try {
                ((JavascriptExecutor) driver).executeScript(javaScript, args);
            } catch (JavascriptException e) {
                logger.warn(String.format("Unable to execute javascript: [%s]", javaScript));
            }
        }
    }

    void setAttribute(WebElement element, String attribute, String value) {
        execute("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attribute, value);
    }

    /**
     * Current URI of UI host
     *
     * @return String URI
     */
    public String getAddress() {
        return driver.getCurrentUrl();
    }

    WebElement findUiElement(By locator) {
        List<WebElement> elements = findUiElements(locator);
        if (elements.size() == 0) {
            logger.warn(String.format("WARNING: Unable to find any element %s", locator.toString()));
            return null;
        } else {
            return elements.get(0);
        }
    }

    List<WebElement> findUiElements(By locator) {
        List<WebElement> elements = new ArrayList<>();
        try {
            elements = driver.findElements(locator);
        } catch (WebDriverException e) {
            logger.warn(String.format("WARNING: Unable to find any element %s", locator.toString()));
        }
        return elements;
    }

    boolean waitUntilVisible(WebElement element) {
        try {
            new WebDriverWait(driver, getConfig().getTimeout())
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException | NullPointerException e) {
            String warningMessageFormat = "WARNING: WebElement failed to be displayed within %d seconds";
            logger.warn(String.format(warningMessageFormat, getConfig().getTimeout()));
            return false;
        }
        return true;
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
        driver = (WebDriver) driverInstantiation.execute();
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

    public void switchTo(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    public void switchTo() {
        driver.switchTo().defaultContent();
    }
}
