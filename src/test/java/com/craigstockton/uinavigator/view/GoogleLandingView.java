package com.craigstockton.uinavigator.view;

import com.craigstockton.uinavigator.Element;
import com.craigstockton.uinavigator.View;

public class GoogleLandingView extends View implements GoogleLandingViewValidatable {

    private final static String description = "'Google Landing' view";
    private final static String locatorType = Element.LocatorType.ID;
    private final static String locatorValue = "viewport";
    private final static String siteDomain = "http://www.google.com/";

    private GoogleLandingView() {
        super(siteDomain, Element.getInstance(description, locatorType, locatorValue));
    }

    public static GoogleLandingView directNav() {
        GoogleLandingView view = new GoogleLandingView();
        view.load();
        view.confirmElementStates();
        return view;
    }

    @Override
    protected boolean confirmElementStates() {
        boolean statesConfirmed = getViewElement().waitUntilVisible();
        statesConfirmed &= getGoogleImageElement().waitUntilVisible();
        return statesConfirmed;
    }

    @Override
    public String getGoogleImageAlt() {
        return getGoogleImageElement().getTip();
    }

    private Element getGoogleImageElement() {
        return Element.getInstance("'Google Image'", Element.LocatorType.ID, "hplogo", getViewElement()).setTipAttribute("alt");
    }
}
