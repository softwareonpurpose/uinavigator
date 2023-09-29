package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    public static GetByCssFromRoot getInstance(String locatorType, String locatorValue) {
        return new GetByCssFromRoot(locatorType, locatorValue);
    }

    abstract WebElement execute();
}
