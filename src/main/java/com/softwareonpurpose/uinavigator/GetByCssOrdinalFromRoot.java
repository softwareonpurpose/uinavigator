package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetByCssOrdinalFromRoot extends GetWebElementBehavior {
    private final int ordinal;

    private GetByCssOrdinalFromRoot(String css, Integer ordinal) {
        super(css);
        this.ordinal = ordinal;
    }

    public static GetWebElementBehavior getInstance(String locatorType, String locatorValue, Integer ordinal) {
        return new GetByCssOrdinalFromRoot(composeCss(locatorType, locatorValue), ordinal);
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
