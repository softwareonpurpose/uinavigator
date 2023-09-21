package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

public class GetElementWithId extends GetWebElementBehavior {
    public GetElementWithId(String locatorValue) {
        super(UiLocatorType4.ID, locatorValue, null, null);
    }

    public static GetElementWithId getInstance(String locatorValue) {
        return new GetElementWithId(locatorValue);
    }

    @Override
    public WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (Exception e) {
            LogManager.getLogger("").warn(String.format("Element NOT FOUND using %s", locator));
        }
        return null;
    }

    @Override
    protected String composeCss(String locatorType, String locatorValue, Integer ordinal, String parentCss) {
        return String.format("%s%s", UiLocatorType4.ID, locatorValue);
    }
}
