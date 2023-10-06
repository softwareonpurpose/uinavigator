package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetFromAncestor extends GetWebElementBehavior {
    private GetFromAncestor(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        super(locatorType, locatorValue, ordinal, ancestor);
    }

    public static GetFromAncestor getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        return new GetFromAncestor(locatorType, locatorValue, ordinal, ancestor);
    }

    public static GetFromAncestor getInstance(String locatorType, String locatorValue, UiElement4 ancestor) {
        return new GetFromAncestor(locatorType, locatorValue, null, ancestor);
    }

    @Override
    WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(extractExceptionMessage(e));
            return null;
        }
    }
}
