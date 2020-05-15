package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverLoad;
import org.openqa.selenium.WebDriver;

public class WebDriverLoad extends UiDriverLoad {
    public WebDriverLoad(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public void execute(String address) {
        ((WebDriver) getDriver.execute()).get(address);
    }
}
