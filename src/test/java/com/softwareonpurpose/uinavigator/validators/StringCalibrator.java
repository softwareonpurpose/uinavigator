package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class StringCalibrator extends Calibrator {

    private final static String DESCRIPTION = "String";
    private final String actual;
    private final String expected;
    private final String stringDescription;

    private StringCalibrator(String description, String expected, String actual) {
        super(DESCRIPTION, expected, actual);
        this.stringDescription = description;
        this.actual = actual;
        this.expected = expected;
    }

    public static StringCalibrator construct(String description, String expected, String actual) {
        return new StringCalibrator(description, expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify(String.format("%s %s contains %s", stringDescription, actual, expected), actual.contains(expected), true);
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
