package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class IntegerValidator extends Calibrator {

    private static final String description = "Integer";
    private final Integer expected;
    private final Integer actual;

    private IntegerValidator(Integer expected, Integer actual) {
        super(description, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static IntegerValidator getInstance(Integer expected, Integer actual) {
        return new IntegerValidator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Integer", expected, actual);
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
