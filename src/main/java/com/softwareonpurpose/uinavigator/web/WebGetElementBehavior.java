package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetElementBehavior;
import org.openqa.selenium.WebElement;

public interface WebGetElementBehavior extends GetElementBehavior {
    @Override
    WebElement execute();
}
