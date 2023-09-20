package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

public class GetElementWithTag extends GetWebElementBehavior {
    public GetElementWithTag(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        super(locatorType, locatorValue, ordinal, parent);

    }

    public static GetElementWithTag getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        return new GetElementWithTag(locatorType, locatorValue, ordinal, parent);
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
}
