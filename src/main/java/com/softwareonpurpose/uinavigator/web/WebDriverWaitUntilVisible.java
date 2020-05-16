package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverWaitUntilVisible;
import com.softwareonpurpose.uinavigator.UiElementGet;
import com.softwareonpurpose.uinavigator.UiNavigatorConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

public class WebDriverWaitUntilVisible extends UiDriverWaitUntilVisible {
    public WebDriverWaitUntilVisible(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public boolean execute(UiElementGet getElement) {
        final long timeout = UiNavigatorConfiguration.getInstance().getTimeout();
        try {
            WebDriver driver = (WebDriver) getDriver.execute();
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOf((WebElement) getElement.execute()));
        } catch (WebDriverException | NullPointerException e) {
            String warningMessageFormat = "WARNING: WebElement failed to be displayed within %d seconds";
            final String message = String.format(warningMessageFormat, timeout);
            LoggerFactory.getLogger(this.getClass()).warn(message);
            return false;
        }
        return true;
    }
}
