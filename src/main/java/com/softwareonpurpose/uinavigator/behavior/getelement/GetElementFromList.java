package com.softwareonpurpose.uinavigator.behavior.getelement;

import org.openqa.selenium.WebElement;

public class GetElementFromList implements GetWebElementBehavior {
    public static GetElementFromList getInstance() {
        return new GetElementFromList();
    }

    @Override
    public WebElement execute() {
        return null;
    }
}
