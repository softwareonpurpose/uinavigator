/**
 * Copyright 2015 Craig A. Stockton
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UiHost {

    private static UiHost uiHost;
    private static Configuration config;
    private final DriverInstantiation driverInstantiation;
    private WebDriver driver;

    private UiHost(DriverInstantiation driverInstantiation) {
        this.driverInstantiation = driverInstantiation;
        instantiateUiDriver();
    }

    /**
     * @return UiHost singleton instance with default WebDriver
     */
    public static UiHost getInstance() {
        if (uiHost == null) {
            uiHost = new UiHost(DefaultDriverInstantiation.getInstance());
        }
        return uiHost;
    }

    /**
     * @param driverInstantiation DriverInstantiation
     * @return Selenium WebDriver
     */
    public static UiHost getInstance(DriverInstantiation driverInstantiation) {
        if (uiHost == null) {
            uiHost = new UiHost(driverInstantiation);
        }
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

    /**
     * Navigate browser to the provided URI.
     *
     * @param uri String URI
     */
    public void load(String uri) {
        getLogger().info(String.format("Navigate browser to %s", uri));
        getDriver().get(uri);
    }

    public void execute(String script) {
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript(script);
        }
    }

    /**
     * @return String which is the current URI of the browser
     */
    public String getUri() {
        return getDriver().getCurrentUrl();
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
            getLogger().warn(String.format("WARNING: Unable to find any element using locator '%s'", locator.toString()));
        }
        return null;
    }

    public void selectFrame(String frameId) {
        getDriver().switchTo().frame(frameId);
    }

    public void selectWindow() {
        getDriver().switchTo().defaultContent();
    }

    /**
     * @param locator A Selenium.By WebElement locator
     * @return A List of WebElements within the current web page
     */
    List<WebElement> findUiElements(By locator) {
        List<WebElement> elements;
        try {
            elements = getDriver().findElements(locator);
        } catch (WebDriverException e) {
            getLogger().warn(String.format("WARNING: Unable to find any element using locator '%s'", locator.toString()));
            return null;
        }
        return elements;
    }

    /**
     * @param locator A Selenium.By WebElement
     * @return boolean Indicates whether the WebElement described by the By locator was visible within a defined timeout period
     */
    boolean waitUntilVisible(By locator) {
        try {
            new WebDriverWait(getDriver(), getConfig().timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            getLogger().warn(String.format("WARNING: UiElement '%s' failed to be displayed within %d seconds", locator.toString(),
                    getConfig().timeout));
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

    private WebDriver getDriver() {
        return driver;
    }

    private void instantiateUiDriver() {
        driver = driverInstantiation.execute();
    }

    private Logger getLogger() {
        return LogManager.getLogger(this.getClass());
    }

    private static class DefaultDriverInstantiation implements DriverInstantiation {

        public static DefaultDriverInstantiation getInstance() {
            return new DefaultDriverInstantiation();
        }

        @Override
        public WebDriver execute() {
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
            ChromeDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(getConfig().timeout, TimeUnit.SECONDS);
            return driver;
        }
    }
}
