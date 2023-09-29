package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetElementWithTag extends GetWebElementBehavior {

    private final transient int index;

    public GetElementWithTag(String locatorValue, Integer ordinal, UiElement4 parent) {
        super(locatorValue, ordinal, parent);
        index = ordinal == null || ordinal < 0 ? 0 : ordinal - 1;
    }

    public static GetElementWithTag getInstance(String locatorValue, Integer ordinal, UiElement4 parent) {
        return new GetElementWithTag(locatorValue, ordinal, parent);
    }

    @Override
    public WebElement execute() {
        List<WebElement> elements = UiNavigator.getInstance().getDriver().findElements(locator);
        if (index < elements.size()) {
            return elements.get(index);
        }
        LogManager.getLogger("").warn(String.format("Element NOT FOUND using %s", locator));
        return null;
    }

    @Override
    protected String composeCss(String locatorValue) {
        String parentCss = parent == null ? "" : parent.getCss();
        return String.format("%s %s%s", parentCss, UiLocatorType4.TAG, locatorValue);
    }
}
