package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class ClassCalibrator extends Calibrator {

    private static final String description = "Class";
    private final Class actual;
    private final Class expected;

    private ClassCalibrator(Class actual, Class expected) {
        super(description, expected, actual);
        this.actual = actual;
        this.expected = expected;
    }

    public static ClassCalibrator getInstance(Class expected, Class actual) {
        return new ClassCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Canonical Name", expected.getCanonicalName(), actual.getCanonicalName());
    }
}
