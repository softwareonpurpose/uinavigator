package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetByCssFromRootOrdinal extends GetWebElementBehavior {
    private final By.ByCssSelector locator;
    private final int ordinal;

    public GetByCssFromRootOrdinal(String locatorType, String locatorValue, Integer ordinal) {
        this.ordinal = ordinal;
        locator = new By.ByCssSelector(String.format("%s%s", locatorType, locatorValue));
    }

    public static GetWebElementBehavior getInstance(String locatorType, String locatorValue, Integer ordinal) {
        return new GetByCssFromRootOrdinal(locatorType, locatorValue, ordinal);
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
