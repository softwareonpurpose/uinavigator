package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByCssFromRoot extends GetWebElementBehavior {
    private GetByCssFromRoot(String css) {
        super(css, null);
    }

    public static GetByCssFromRoot getInstance(String locatorType, String locatorValue) {
        return new GetByCssFromRoot(composeCss(locatorType, locatorValue));
    }

    @Override
    public WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(extractExceptionMessage(e));
            return null;
        }
    }
}
