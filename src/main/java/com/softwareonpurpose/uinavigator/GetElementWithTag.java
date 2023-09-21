package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

public class GetElementWithTag extends GetWebElementBehavior {

    public GetElementWithTag(String locatorValue, Integer ordinal, UiElement4 parent) {
        super(locatorValue, ordinal, parent);

    }

    public static GetElementWithTag getInstance(String locatorValue, Integer ordinal, UiElement4 parent) {
        return new GetElementWithTag(locatorValue, ordinal, parent);
    }

    @Override
    public WebElement execute() {
        try {
            return hasParent() && isParentLocatedByClass() ? getParent().findElement(locator) : UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (Exception e) {
            LogManager.getLogger("").warn(String.format("Element NOT FOUND using %s", locator));
        }
        return null;
    }

    @Override
    protected String composeCss(String locatorValue, Integer ordinal) {
        String thisCss = String.format("%s", locatorValue);
        thisCss += ordinal == null ? "" : String.format(":nth-of-type(%s)", ordinal);
        String parentCss = isParentLocatedByClass() ? "" : String.format("%s ", getParentCss());
        return String.format("%s%s", parentCss, thisCss);
    }
}
