package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiElementGet;
import com.softwareonpurpose.uinavigator.web.WebUiSwitchToFrame;
import com.softwareonpurpose.uinavigator.web.WebUiSwitchToView;

public abstract class UiSwitchTo {

    public static UiSwitchTo getFrameInstance(UiElementGet getBehavior) {
        return new WebUiSwitchToFrame((WebUiElementGet) getBehavior);
    }

    public static UiSwitchTo getViewInstance() {
        return new WebUiSwitchToView();
    }

    public abstract void execute();
}
