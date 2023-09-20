package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

public class GetElementWithTag extends GetWebElementBehavior {
    public GetElementWithTag(String locatorType, String locatorValue, Integer ordinal, String parentCSS) {
        super(locatorType, locatorValue, ordinal, parentCSS);

    }

    public static GetElementWithTag getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        String parentCss = parent == null ? "" : String.format("%s ", parent.getCss());
        return new GetElementWithTag(locatorType, locatorValue, ordinal, parentCss);
    }

    @Override
    public WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (Exception e) {
            LogManager.getLogger("").warn(String.format("Element NOT FOUND using %s", locator));
        }
        return null;
    }
}
