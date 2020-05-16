package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiDriverSwitchTo;
import com.softwareonpurpose.uinavigator.UiElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverSwitchTo extends UiDriverSwitchTo {
    public WebDriverSwitchTo(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public void execute(UiElement element) {
        WebElement webElement = (WebElement) element.getLocator().execute();
        final WebDriver.TargetLocator switchTo = ((WebDriver) getDriver.execute()).switchTo();
        if ("iframe".equals(webElement.getTagName())) {
            switchTo.frame(webElement);
        } else {
            switchTo.defaultContent();
        }
    }
}
