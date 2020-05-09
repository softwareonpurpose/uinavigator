package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ElementClick;
import com.softwareonpurpose.uinavigator.ElementBehaviors;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import static com.softwareonpurpose.uinavigator.ElementBehaviors.reportException;

public class WebElementClick extends ElementClick {
    public WebElementClick(String description, WebUiGetElement getElement) {
        super(description, getElement);
    }

    public void execute() {
        WebElement element = (WebElement) getElement.execute();
        final String message = "BLOCKED: Unable to click %s using hierarchy %s";
        final String errorMessage = String.format(message, description, getElement.toString());
        if (element == null || "".equals(element.getTagName())) {
            if (ElementBehaviors.isLoggingSuppressed()) {
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
