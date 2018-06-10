package com.softwareonpurpose.uinavigator.validators.webelements;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.softwareonpurpose.uinavigator.validators.webelement.WebElementExpected;
import com.softwareonpurpose.uinavigator.validators.webelement.WebElementCalibrator;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementsCalibrator extends Calibrator {
    private static final String DESCRIPTION = "WebElement";

    private WebElementsCalibrator(List<WebElementExpected> expected, List<WebElement> actual) {
        super(DESCRIPTION, expected, actual);
        int index = 0;
        for (WebElementExpected expectedElement : expected) {
            addChildCalibrator(WebElementCalibrator.getInstance(expectedElement, actual.get(index)));
            index += 1;
        }
    }

    public static WebElementsCalibrator getInstance(List<WebElementExpected> expected, List<WebElement> actual) {
        return new WebElementsCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {

    }
}
