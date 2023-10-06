package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetFromAncestorOrdinal extends GetWebElementBehavior {
    private final UiElement4 ancestor;

    protected GetFromAncestorOrdinal(String locatorType, String locatorValue, UiElement4 ancestor) {
        super(locatorType, locatorValue, null, ancestor);
        this.ancestor = ancestor;
    }

    public static GetFromAncestorOrdinal getInstance(String locatorType, String locatorValue, UiElement4 ancestor) {
        return new GetFromAncestorOrdinal(locatorType, locatorValue, ancestor);
    }

    @Override
    WebElement execute() {
        try {
            return ancestor.getElement().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(extractExceptionMessage(e));
            return null;
        }
    }
}
