package com.softwareonpurpose.uinavigator.validators.webelement;

import com.softwareonpurpose.calibrator4test.Calibrator;
import org.openqa.selenium.WebElement;

public class WebElementCalibrator extends Calibrator {

    private final static String description = "WebElement";
    private final WebElement actual;
    private final WebElementExpected expected;

    private WebElementCalibrator(WebElementExpected expected, WebElement actual) {
        super(description, expected, actual);
        this.actual = actual;
        this.expected = expected;
    }

    public static WebElementCalibrator construct(WebElementExpected expected, WebElement actual) {
        return new WebElementCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Id", expected.getId(), actual.getAttribute("id"));
        verify("Class", expected.getClasses(), actual.getAttribute("class"));
        verify("Tag", expected.getTag(), actual.getTagName());
        verify("Href", expected.getHref(), actual.getAttribute("href"));
    }
}
