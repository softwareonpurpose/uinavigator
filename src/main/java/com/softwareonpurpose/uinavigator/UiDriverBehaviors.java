package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverBehaviors;

public abstract class UiDriverBehaviors {
    private final UiDriverLoad load;

    public UiDriverBehaviors() {
        load = UiDriverLoad.getInstance();
    }

    public static UiDriverBehaviors getInstance() {
        return new WebDriverBehaviors();
    }

    public void load(String address) {
        load.execute(address);
    }
}
