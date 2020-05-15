package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverQuit;
import org.openqa.selenium.WebDriver;

public class WebDriverQuit extends UiDriverQuit {
    public WebDriverQuit(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public void execute() {
        if (getDriver != null) {
            ((WebDriver) getDriver.execute()).quit();
        }
    }
}
