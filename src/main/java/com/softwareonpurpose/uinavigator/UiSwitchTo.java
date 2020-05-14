package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGet;
import com.softwareonpurpose.uinavigator.web.WebSwitchToFrame;
import com.softwareonpurpose.uinavigator.web.WebSwitchToView;

public abstract class UiSwitchTo {

    public static UiSwitchTo getFrameInstance(UiElementGet getBehavior) {
        return new WebSwitchToFrame((WebElementGet) getBehavior);
    }

    public static UiSwitchTo getViewInstance() {
        return new WebSwitchToView();
    }

    public abstract void execute();
}
