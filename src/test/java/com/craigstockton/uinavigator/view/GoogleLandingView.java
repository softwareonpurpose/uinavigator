package com.craigstockton.uinavigator.view;

import com.craigstockton.uinavigator.HtmlElement;
import com.craigstockton.uinavigator.View;

public class GoogleLandingView extends View implements GoogleLandingViewValidatable {

    private final static String description = "'Google Landing' view";
    private final static String locatorType = HtmlElement.LocatorType.ID;
    private final static String locatorValue = "viewport";
    private final static String siteDomain = "http://www.google.com/";

    private GoogleLandingView() {
        super(siteDomain, HtmlElement.getInstance(description, locatorType, locatorValue));
    }

    public static GoogleLandingView directNav() {
        GoogleLandingView view = new GoogleLandingView();
        view.navTo();
        view.confirmElementStates();
        return view;
    }

    @Override
    protected boolean confirmElementStates() {
        boolean statesConfirmed = viewElement.waitUntilVisible();
        statesConfirmed &= getGoogleImageElement().waitUntilVisible();
        return statesConfirmed;
    }

    @Override
    public String getGoogleImageAlt() {
        return getGoogleImageElement().getTip();
    }

    private HtmlElement getGoogleImageElement() {
        return HtmlElement.getInstance("'Google Image'", HtmlElement.LocatorType.ID, "hplogo", viewElement)
                .setTipAttribute("alt");
    }
}
