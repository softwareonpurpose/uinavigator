package com.softwareonpurpose.uinavigator.validators;

import com.softwareonpurpose.validator4test.Validator;

public class BooleanValidator extends Validator {

    private static final String description = "Boolean";
    private final Boolean expected;
    private final Boolean actual;

    private BooleanValidator(Boolean expected, Boolean actual) {
        super(description, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static BooleanValidator getInstance(Boolean expected, Boolean actual) {
        return new BooleanValidator(expected, actual);
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
