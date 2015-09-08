package com.craigstockton.uinavigator.view;

public class GoogleLandingViewExpected implements GoogleLandingViewValidatable {

    private GoogleLandingViewExpected() {
    }

    public static GoogleLandingViewExpected compose() {
        return new GoogleLandingViewExpected();
    }

    public String validate(GoogleLandingViewValidatable actual) {
        return GoogleLandingViewValidator.getInstance(this, actual).validate();
    }

    @Override
    public String getGoogleImageAlt() {
        return "Google";
    }
}
