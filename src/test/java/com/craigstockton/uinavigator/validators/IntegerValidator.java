package com.craigstockton.uinavigator.validators;

import com.craigstockton.validator4test.Validator;

public class IntegerValidator extends Validator {

    private static final String description = "Integer";
    private final Integer expected;
    private final Integer actual;

    private IntegerValidator(Integer expected, Integer actual) {
        super(description);
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
