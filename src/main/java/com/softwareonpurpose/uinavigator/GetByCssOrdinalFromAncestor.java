package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetByCssOrdinalFromAncestor extends GetWebElementBehavior {
    private final int ordinal;

    private GetByCssOrdinalFromAncestor(String css, Integer ordinal) {
        super(css);
        this.ordinal = ordinal == null || ordinal < 1 ? 0 : ordinal;
    }

    public static GetByCssOrdinalFromAncestor getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        String css = composeCss(locatorType, locatorValue, ancestor);
        return new GetByCssOrdinalFromAncestor(css, ordinal);
    }

    @Override
    WebElement execute() {
        int index = ordinal - 1;
        List<WebElement> elements = UiNavigator.getInstance().getDriver().findElements(locator);
        if (index < elements.size()) {
            return elements.get(index);
        } else {
            String message = String.format("Unable to locate element %s ordinal %d", locator, ordinal);
            LogManager.getLogger("").warn(message);
            return null;
        }
    }
}
