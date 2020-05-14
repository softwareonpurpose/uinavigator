package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverLoad;

public abstract class UiDriverLoad {
    public static UiDriverLoad getInstance() {
        return new WebDriverLoad();
    }

    public abstract void execute(String address);
}
