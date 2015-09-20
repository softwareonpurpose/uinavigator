package com.craigstockton.uinavigator.view;

import com.craigstockton.uinavigator.UiElement;
import com.craigstockton.uinavigator.UiView;

public class GoogleLandingView extends UiView implements GoogleLandingViewValidatable {

    private final static String description = "'Google Landing' view";
    private final static String locatorType = UiElement.LocatorType.ID;
    private final static String locatorValue = "viewport";
    private final static String siteDomain = "http://www.google.com/";

    private GoogleLandingView() {
        super(siteDomain, UiElement.getInstance(description, locatorType, locatorValue));
    }

    public static GoogleLandingView directNav() {
        GoogleLandingView view = new GoogleLandingView();
        view.load();
        view.confirmElementStates();
        return view;
    }

    @Override
    protected boolean confirmElementStates() {
        boolean statesConfirmed = getElement().waitUntilVisible();
        statesConfirmed &= getGoogleImageElement().waitUntilVisible();
        return statesConfirmed;
    }

    @Override
    public String getGoogleImageAlt() {
        return getGoogleImageElement().getTip();
    }

    private UiElement getGoogleImageElement() {
        return UiElement.getInstance("'Google Image'", UiElement.LocatorType.ID, "hplogo", getElement()).setTipAttribute("alt");
    }
}
