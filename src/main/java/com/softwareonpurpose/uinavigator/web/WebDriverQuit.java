package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverQuit;
import org.openqa.selenium.WebDriver;

public class WebDriverQuit extends UiDriverQuit {
    @Override
    public void execute() {
        ((WebDriver) UiDriverGet.execute()).quit();
    }
}
