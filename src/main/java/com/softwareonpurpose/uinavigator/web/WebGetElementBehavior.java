package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetElementBehavior;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class WebGetElementBehavior extends GetElementBehavior {
    protected WebGetElementBehavior(By locator) {
        super(locator);
    }

    @Override
    protected abstract WebElement execute();
}
