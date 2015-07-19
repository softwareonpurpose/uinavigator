package com.craigstockton.uinavigator;

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
    private WebDriver driver;
    private static final int waitLimit = 13;
    private final static String chromeDriverPath = "src/test/resources/chromedriver.exe";

    private BrowserHost() {
        instantiateUiDriver();
    }

    public static BrowserHost getInstance() {
        if (uiHost == null) {
            uiHost = new BrowserHost();
        }
        return uiHost;
    }

    public static void quitInstance() {
        if (uiHost == null) {
            return;
        }
        uiHost.quit();
        uiHost = null;
    }

    public void load(String uri) {
        getLogger().info(String.format("Navigate browser to %s", uri));
        getDriver().get(uri);
    }

    public WebElement findUiElement(By locator) {
        List<WebElement> elements = findUiElements(locator);
        if (elements.size() > 0) {
            return elements.get(0);
        } else {
            getLogger().warn(String.format("WARNING: Unable to locate element '%s'", locator.toString()));
        }
        return null;
    }

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
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                driver = new ChromeDriver(capabilities);
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
}
