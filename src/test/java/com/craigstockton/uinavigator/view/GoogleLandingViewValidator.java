package com.craigstockton.uinavigator.view;

import com.craigstockton.validator4test.Validator;

public class GoogleLandingViewValidator extends Validator {

    private final static String description = "'Google Landing' view";
    private final GoogleLandingViewValidatable actual;
    private final GoogleLandingViewValidatable expected;

    private GoogleLandingViewValidator(GoogleLandingViewValidatable expected, GoogleLandingViewValidatable actual) {
        super(description);
        this.expected = expected;
        this.actual = actual;
    }

    public static GoogleLandingViewValidator getInstance(GoogleLandingViewValidatable expected,
            GoogleLandingViewValidatable actual) {
        return new GoogleLandingViewValidator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("'Google Image' alt text", expected.getGoogleImageAlt(), actual.getGoogleImageAlt());
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
