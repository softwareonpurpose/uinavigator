package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiGetElement;
import com.softwareonpurpose.uinavigator.web.WebUiSwitchToFrame;
import com.softwareonpurpose.uinavigator.web.WebUiSwitchToView;

public abstract class UiSwitchTo {

    public static UiSwitchTo getFrameInstance(UiGetElement getBehavior) {
        return new WebUiSwitchToFrame((WebUiGetElement) getBehavior);
    }

    public static UiSwitchTo getViewInstance() {
        return new WebUiSwitchToView();
    }

    public abstract void execute();
}
