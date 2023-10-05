package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByCssFromAncestor extends GetWebElementBehavior {
    private GetByCssFromAncestor(String css) {
        super(css, null);
    }

    public static GetByCssFromAncestor getInstance(String locatorType, String locatorValue, UiElement4 ancestor) {
        return new GetByCssFromAncestor(composeCss(locatorType, locatorValue, ancestor));
    }

    @Override
    WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(extractExceptionMessage(e));
            return null;
        }
    }
}
