package com.softwareonpurpose.uinavigator.behavior.getelement;

import org.openqa.selenium.WebElement;

public class GetElementDirectly implements GetWebElementBehavior {
    public static GetElementDirectly getInstance() {
        return new GetElementDirectly();
    }

    @Override
    public WebElement execute() {
        return null;
    }
}
