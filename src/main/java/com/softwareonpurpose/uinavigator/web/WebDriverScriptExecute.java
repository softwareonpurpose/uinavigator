package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverScriptExecute;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

public class WebDriverScriptExecute extends UiDriverScriptExecute {
    public WebDriverScriptExecute(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public void execute(String script, Object[] args) {
        final WebDriver driver = (WebDriver) getDriver.execute();
        if (driver instanceof JavascriptExecutor) {
            try {
                ((JavascriptExecutor) driver).executeScript(script, args);
            } catch (JavascriptException e) {
                LoggerFactory.getLogger(this.getClass()).warn(String.format("Unable to execute javascript: [%s]", script));
            }
        }
    }
}
