/**
 * Copyright 2017 Craig A. Stockton
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

import java.util.List;

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
     * @return UiHost singleton instance with default WebDriver
     */
    public static UiHost getInstance() {
        if (uiHost == null) {
            uiHost = new UiHost();
        }
        return uiHost;
    }

    /**
     * @param driverInstantiation DriverInstantiation
     * @return Selenium WebDriver
     */
    public static UiHost getInstance(DriverInstantiation driverInstantiation) {
        setDriverInstantiation(driverInstantiation);
        quitInstance();
        uiHost = new UiHost();
        return uiHost;
    }

    public static Configuration getConfig() {
        if (config == null) {
            config = Configuration.getInstance();
        }
        return config;
    }

    public static void quitInstance() {
        if (uiHost != null) {
            uiHost.quit();
            uiHost = null;
        }
    }

    public static void setDriverInstantiation(DriverInstantiation driverInstantiation) {
        UiHost.driverInstantiation = driverInstantiation;
    }

    /**
     * Navigate browser to the provided URI.
     *
     * @param uri String URI
     */
    public void load(String uri) {
        logger.info(String.format("Navigate browser to %s", uri));
        driver.get(uri);
    }

    public void execute(String script) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(script);
        }
    }

    void execute(WebElement element, String attribute, String value) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attribute, value);
        }
    }

    /**
     * @return String which is the current URI of the browser
     */
    public String getUri() {
        return driver.getCurrentUrl();
    }

    /**
     * @param locator A Selenium.By WebElement locator
     * @return WebElement within the current web page
     */
    WebElement findUiElement(By locator) {
        List<WebElement> elements = findUiElements(locator);
        if (elements.size() > 0) {
            return elements.get(0);
        } else {
            logger.warn(String.format("WARNING: Unable to find any element using locator '%s'", locator.toString()));
        }
        return null;
    }

    public void selectFrame(String frameId) {
        driver.switchTo().frame(frameId);
    }

    public void selectWindow() {
        driver.switchTo().defaultContent();
    }

    /**
     * @param locator A Selenium.By WebElement locator
     * @return A List of WebElements within the current web page
     */
    List<WebElement> findUiElements(By locator) {
        List<WebElement> elements;
        try {
            elements = driver.findElements(locator);
        } catch (WebDriverException e) {
            logger.warn(String.format("WARNING: Unable to find any element using locator '%s'", locator.toString()));
            return null;
        }
        return elements;
    }

    /**
     * @param element A Selenium WebElement
     * @return boolean Indicates whether the WebElement was visible within a defined timeout period
     */
    boolean waitUntilVisible(WebElement element) {
        try {
            new WebDriverWait(driver, getConfig().getTimeout())
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            String warningMessageFormat = "WARNING: UiElement '%s' failed to be displayed within %d seconds";
            logger.warn(String.format(warningMessageFormat, element.toString(), getConfig().getTimeout()));
            return false;
        }
        return true;
    }

    /**
     * @return String which is the name of the current WebDriver
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
