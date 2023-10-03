package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetByCssFromRootOrdinal extends GetWebElementBehavior {
    private final int ordinal;

    private GetByCssFromRootOrdinal(By.ByCssSelector locator, Integer ordinal) {
        super(locator);
        this.ordinal = ordinal;
    }

    public static GetWebElementBehavior getInstance(String locatorType, String locatorValue, Integer ordinal) {
        By.ByCssSelector locator = composeCss(locatorType, locatorValue);
        return new GetByCssFromRootOrdinal(locator, ordinal);
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
