package com.craigstockton.uinavigator.validators;

import com.craigstockton.validator4test.Validator;

public class ClassValidator extends Validator {

    private static final String description = "Class";
    private final Class actual;
    private final Class expected;

    private ClassValidator(Class actual, Class expected) {
        super(description);
        this.actual = actual;
        this.expected = expected;
    }

    public static ClassValidator getInstance(Class expected, Class actual) {
        return new ClassValidator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Canonical Name", expected.getCanonicalName(), actual.getCanonicalName());
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
