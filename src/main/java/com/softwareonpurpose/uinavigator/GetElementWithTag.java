package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

public class GetElementWithTag extends GetWebElementBehavior {
    public GetElementWithTag(String locatorType, String locatorValue, Integer ordinal, String parentCSS) {
        super(locatorType, locatorValue, ordinal, parentCSS);

    }

    public static GetElementWithTag getInstance(String locatorType, String locatorValue, Integer ordinal, String parentCss) {
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

    @Override
    protected String composeCss(String locatorType, String locatorValue, Integer ordinal, String parentCss) {
        String thisCss = String.format("%s", locatorValue);
        thisCss += ordinal == null ? "" : String.format(":nth-of-type(%s)", ordinal);
        return String.format("%s%s", parentCss, thisCss);
    }
}
