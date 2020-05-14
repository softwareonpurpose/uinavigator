package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverBehaviors;

public abstract class UiDriverBehaviors {
    public UiDriverBehaviors() {

    }

    public static UiDriverBehaviors getInstance() {
        return new WebDriverBehaviors();
    }
}
