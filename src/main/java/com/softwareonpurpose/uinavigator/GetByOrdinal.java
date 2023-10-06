package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetByOrdinal extends GetWebElementBehavior {
    private GetByOrdinal(String css, Integer ordinal) {
        super(css, ordinal);
    }

    public static GetByOrdinal getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        return new GetByOrdinal(composeCss(locatorType, locatorValue, ancestor), ordinal);
    }

    public static GetByOrdinal getInstance(String locatorType, String locatorValue, Integer ordinal) {
        return new GetByOrdinal(composeCss(locatorType, locatorValue), ordinal);
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
