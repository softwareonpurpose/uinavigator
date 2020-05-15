package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverGetAddress;
import org.openqa.selenium.WebDriver;

public class WebDriverGetAddress extends UiDriverGetAddress {
    public WebDriverGetAddress(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public String execute() {
        return ((WebDriver) getDriver.execute()).getCurrentUrl();
    }
}
