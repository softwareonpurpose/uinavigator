package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverState;
import org.openqa.selenium.WebDriver;

public class WebDriverState extends UiDriverState {
    public WebDriverState(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public String execute(String[] identifiers) {
        final WebDriver driver = (WebDriver) getDriver.execute();
        return CookieViewer.getInstance(driver).getCookieValue(identifiers[0], identifiers[1], identifiers[2]);
    }
}
