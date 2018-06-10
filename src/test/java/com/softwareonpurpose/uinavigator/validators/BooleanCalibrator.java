package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class BooleanCalibrator extends Calibrator {

    private static final String description = "Boolean";
    private final Boolean expected;
    private final Boolean actual;

    private BooleanCalibrator(Boolean expected, Boolean actual) {
        super(description, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static BooleanCalibrator getInstance(Boolean expected, Boolean actual) {
        return new BooleanCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Boolean", expected, actual);
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
