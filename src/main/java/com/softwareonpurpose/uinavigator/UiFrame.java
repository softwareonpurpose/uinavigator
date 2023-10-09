package com.softwareonpurpose.uinavigator;

public class UiFrame {
    public static UiFrame getInstance() {
        return new UiFrame();
    }

    public boolean isDisplayed() {
        //  TODO:  at construction, initialize a 'GetWebElement' behavior to get the element itself and return properties of it
        return false;
    }

    private void getFrame() {
        /*  TODO:   include logic here which switches the underlying WebDriver to this specific frame
                    Still need to determine whether each GetWebElementBehavior object will need a logical switch to
                    behave differently if the ancestor is an 'iframe' OR (and better) there will be a GetFrame
                    behavior either in addition to the GetWebElementBehavior OR imbedded in specific behaviors
         */
    }
}
