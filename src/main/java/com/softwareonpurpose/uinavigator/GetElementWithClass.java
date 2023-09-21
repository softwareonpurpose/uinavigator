package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetElementWithClass extends GetWebElementBehavior {
    private final int index;

    protected GetElementWithClass(String locatorValue, Integer ordinal, UiElement4 parent) {
        super(locatorValue, ordinal, parent);
        index = ordinal == null || ordinal < 2 ? 0 : ordinal - 1;
    }

    public static GetWebElementBehavior getInstance(String locatorValue, Integer ordinal, UiElement4 parent) {
        return new GetElementWithClass(locatorValue, ordinal, parent);
    }

    @Override
    WebElement execute() {
        List<WebElement> elements;
        elements = UiNavigator.getInstance().getDriver().findElements(locator);
        if (index < elements.size()) {
            return elements.get(index);
        } else {
            LogManager.getLogger("").warn(String.format("Element NOT FOUND using %s index: %d", locator, index));
            return null;
        }
    }

    @Override
    protected String composeCss(String locatorValue, Integer ordinal) {
        return String.format("%s%s", UiLocatorType4.CLASS, locatorValue);
    }
}
