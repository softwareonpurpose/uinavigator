package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverQuit;

public abstract class UiDriverQuit {
    public static UiDriverQuit getInstance() {
        return new WebDriverQuit();
    }

    public abstract void execute();
}
