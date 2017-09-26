package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.validator4test.Validator;
import org.openqa.selenium.WebElement;

public class WebElementValidator extends Validator {

    private final static String description = "WebElement";
    private final WebElement actual;
    private final WebElement expected;

    private WebElementValidator(WebElement expected, WebElement actual) {
        super(description, expected, actual);
        this.actual = actual;
        this.expected = expected;
    }

    @Override
    protected void executeVerifications() {
        verify("", expected.getAttribute("class"), actual.getAttribute("class"));
    }

    @Override
    protected boolean actualExists() {
        return actual != null;
    }

    @Override
    protected boolean expectedExists() {
        return expected != null;
    }
}
