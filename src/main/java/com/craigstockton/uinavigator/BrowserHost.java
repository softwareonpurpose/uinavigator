/**
 * Copyright 2015 Craig A. Stockton
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.craigstockton.uinavigator;

import com.craigstockton.uinavigator.driverinstantiation.WebDriverInstantiationBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class BrowserHost {

    private static BrowserHost uiHost;
    private final WebDriverInstantiationBehavior driverInstantiation;
    private WebDriver driver;
    private static final int waitLimit = 13;
    private final static String chromeDriverPath = "src/test/resources/chromedriver.exe";

    private BrowserHost(WebDriverInstantiationBehavior driverInstantiation) {

        this.driverInstantiation = driverInstantiation;
        instantiateUiDriver();
    }

    /**
     * @return BrowserHost singleton instance with default WebDriver
     */
    public static BrowserHost getInstance() {
        if (uiHost == null) {
            uiHost = new BrowserHost(DefaultDriverInstantiation.getInstance());
        }
        return uiHost;
    }

    /**
     * @param driverInstantiation WebDriverInstiationBehavior
     * @return Selenium WebDriver
     */
    public static BrowserHost getInstance(WebDriverInstantiationBehavior driverInstantiation) {
        if (uiHost == null) {
            uiHost = new BrowserHost(driverInstantiation);
        }
        return uiHost;
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

    /**
     * @param locator A Selenium.By WebElement locator
     * @return WebElement within the current web page
     */
    public WebElement findUiElement(By locator) {
        List<WebElement> elements = findUiElements(locator);
        if (elements.size() > 0) {
            return elements.get(0);
        } else {
            getLogger().warn(String.format("WARNING: Unable to locate element '%s'", locator.toString()));
        }
        return null;
    }

    /**
     * @param locator A Selenium.By WebElement locator
     * @return A List of WebElements within the current web page
     */
    public List<WebElement> findUiElements(By locator) {
        List<WebElement> elements;
        try {
            elements = getDriver().findElements(locator);
        } catch (WebDriverException e) {
            getLogger().warn(String.format("Unable to find element using locator '%s'", locator.toString()));
            return null;
        }
        return elements;
    }

    /**
     * @param locator A Selenium.By WebElement
     * @return boolean Indicates whether the WebElement described by the By locator was visible within a defiend timeout period
     */
    public boolean waitUntilVisible(By locator) {
        try {
            new WebDriverWait(getDriver(), waitLimit).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            getLogger().warn(String
                    .format("WARNING: Element '%s' failed to be displayed within %d seconds", locator.toString(), waitLimit));
            return false;
        }
        return true;
    }

    /**
     * @return String which is the current URI of the browser
     */
    public String getUri() {
        return getDriver().getCurrentUrl();
    }

    private void quit() {
        driver.quit();
        driver = null;
    }

    private WebDriver getDriver() {
        return driver;
    }

    private void instantiateUiDriver() {
        if (driver == null) {
            if (new File(chromeDriverPath).exists()) {
                driver = driverInstantiation.execute();
                driver.manage().deleteAllCookies();
                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension(1235, 1000));
            } else {
                getLogger().error("BLOCKED: WebDriver executable not found at " + chromeDriverPath);
            }
        }
    }

    private Logger getLogger() {
        return LogManager.getLogger(this.getClass());
    }

    public String getDriverName() {
        return driver.getClass().getName();
    }

    private static class DefaultDriverInstantiation implements WebDriverInstantiationBehavior {

        public static DefaultDriverInstantiation getInstance() {
            return new DefaultDriverInstantiation();
        }

        @Override
        public WebDriver execute() {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            return new ChromeDriver(capabilities);
        }
    }
}
