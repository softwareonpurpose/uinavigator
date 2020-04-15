package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetElementBehavior;
import org.openqa.selenium.WebElement;

public abstract class WebGetElementBehavior extends GetElementBehavior {
    @Override
    public abstract WebElement execute();
}
