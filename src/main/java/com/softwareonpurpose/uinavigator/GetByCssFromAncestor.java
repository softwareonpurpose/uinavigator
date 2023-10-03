package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetByCssFromAncestor extends GetWebElementBehavior {
    private GetByCssFromAncestor(By.ByCssSelector locator) {
        super(locator);
    }

    public static GetByCssFromAncestor getInstance(String locatorType, String locatorValue, UiElement4 ancestor) {
        return null;
    }

    @Override
    WebElement execute() {
        return null;
    }
}
