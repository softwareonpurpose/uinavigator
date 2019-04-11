package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class IntegerCalibrator extends Calibrator {

    private static final String description = "Integer";
    private final Integer expected;
    private final Integer actual;

    private IntegerCalibrator(Integer expected, Integer actual) {
        super(description, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static IntegerCalibrator construct(Integer expected, Integer actual) {
        return new IntegerCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Integer", expected, actual);
    }
}
