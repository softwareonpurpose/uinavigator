package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ClickBehavior;
import com.softwareonpurpose.uinavigator.UiElementBehaviors;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import static com.softwareonpurpose.uinavigator.UiElementBehaviors.reportException;

public class WebClickBehavior extends ClickBehavior {
    public WebClickBehavior(String description, WebGetElementBehavior getElement) {
        super(description, getElement);
    }

    public void execute() {
        WebElement element = (WebElement) getElement.execute();
        final String message = "BLOCKED: Unable to click %s using hierarchy %s";
        final String errorMessage = String.format(message, description, getElement.toString());
        if (element == null || "".equals(element.getTagName())) {
            if (UiElementBehaviors.isLoggingSuppressed()) {
                final String root = "";
                LoggerFactory.getLogger(root).error(errorMessage);
            }
        } else {
            try {
                element.click();
            } catch (WebDriverException | NullPointerException e) {
                reportException(e, errorMessage);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
