package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

public class GetElementWithId extends GetWebElementBehavior {

    public GetElementWithId(String locatorType, String locatorValue, Integer ordinal, String parentCss) {
        super(locatorType, locatorValue, ordinal, parentCss);
    }

    public static GetElementWithId getInstance(String locatorType, String locatorValue, Integer ordinal, UiElement4 parent) {
        String parentCss = parent == null ? "" : String.format("%s ", parent.getCss());
        return new GetElementWithId(locatorType, locatorValue, ordinal, parentCss);
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
