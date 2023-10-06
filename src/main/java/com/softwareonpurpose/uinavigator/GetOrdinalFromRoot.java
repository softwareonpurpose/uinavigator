package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetOrdinalFromRoot extends GetWebElementBehavior {
    private GetOrdinalFromRoot(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        super(locatorType, locatorValue, ordinal, ancestor);
    }

    public static GetOrdinalFromRoot getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 ancestor) {
        return new GetOrdinalFromRoot(locatorType, locatorValue, ordinal, ancestor);
    }

    public static GetOrdinalFromRoot getInstance(String locatorType, String locatorValue, Integer ordinal) {
        return new GetOrdinalFromRoot(locatorType, locatorValue, ordinal, null);
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
