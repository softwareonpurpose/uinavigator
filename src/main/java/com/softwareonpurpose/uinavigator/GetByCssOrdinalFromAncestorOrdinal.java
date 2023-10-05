package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetByCssOrdinalFromAncestorOrdinal extends GetWebElementBehavior {
    private final UiElement4 ancestor;

    protected GetByCssOrdinalFromAncestorOrdinal(String css, Integer ordinal, UiElement4 ancestor) {
        super(css, ordinal);
        this.ancestor = ancestor;
    }

    public static GetByCssOrdinalFromAncestorOrdinal getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        String css = composeCss(locatorType, locatorValue);
        return new GetByCssOrdinalFromAncestorOrdinal(css, ordinal, ancestor);
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
