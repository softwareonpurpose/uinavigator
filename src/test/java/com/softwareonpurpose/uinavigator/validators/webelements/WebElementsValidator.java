package com.softwareonpurpose.uinavigator.validators.webelements;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.softwareonpurpose.uinavigator.validators.webelement.WebElementExpected;
import com.softwareonpurpose.uinavigator.validators.webelement.WebElementValidator;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementsValidator extends Calibrator {
    private static final String DESCRIPTION = "WebElement";

    private WebElementsValidator(List<WebElementExpected> expected, List<WebElement> actual) {
        super(DESCRIPTION, expected, actual);
        int index = 0;
        for (WebElementExpected expectedElement : expected) {
            addChildCalibrator(WebElementValidator.getInstance(expectedElement, actual.get(index)));
            index += 1;
        }
    }

    public static WebElementsValidator getInstance(List<WebElementExpected> expected, List<WebElement> actual) {
        return new WebElementsValidator(expected, actual);
    }

    @Override
    protected void executeVerifications() {

    }
}
