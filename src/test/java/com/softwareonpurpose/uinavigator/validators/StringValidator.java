package com.softwareonpurpose.uinavigator.validators;

import com.craigstockton.validator4test.Validator;

public class StringValidator extends Validator {

    private final static String description = "String";
    private final String actual;
    private final String expected;

    private StringValidator(String expected, String actual) {
        super(description);
        this.actual = actual;
        this.expected = expected;
    }

    public static StringValidator getInstance(String expected, String actual) {
        return new StringValidator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("String contains expected value", actual.contains(expected), true);
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
