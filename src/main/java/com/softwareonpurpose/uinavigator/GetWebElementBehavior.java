package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    public static GetWebElementBehavior getInstance(String locatorType, String locatorValue) {
        return new GetByCssFromRoot(locatorType, locatorValue);
    }

    public static GetWebElementBehavior getInstance(String locatorType, String locatorValue, Integer ordinal) {
        return new GetByCssFromRootOrdinal(locatorType, locatorValue, ordinal);
    }

    abstract WebElement execute();
}
