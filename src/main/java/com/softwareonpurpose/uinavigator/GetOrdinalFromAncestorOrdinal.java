package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetOrdinalFromAncestorOrdinal extends GetWebElementBehavior {
    private final UiElement4 ancestor;

    protected GetOrdinalFromAncestorOrdinal(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        super(locatorType, locatorValue, ordinal, ancestor);
        this.ancestor = ancestor;
    }

    public static GetOrdinalFromAncestorOrdinal getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        return new GetOrdinalFromAncestorOrdinal(locatorType, locatorValue, ordinal, ancestor);
    }

    @Override
    WebElement execute() {
        int index = ordinal - 1;
        List<WebElement> elements = ancestor.getElement().findElements(locator);
        if (index < elements.size()) {
            return elements.get(index);
        } else {
            String message = String.format("Unable to locate element %s ordinal %d", locator, ordinal);
            LogManager.getLogger("").warn(message);
            return null;
        }
    }
}
