package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebGetElementBehavior;
import com.softwareonpurpose.uinavigator.web.WebSwitchToFrame;
import com.softwareonpurpose.uinavigator.web.WebSwitchToView;

public abstract class SwitchToBehavior {

    public static SwitchToBehavior getFrameInstance(GetElementBehavior getBehavior) {
        return new WebSwitchToFrame((WebGetElementBehavior) getBehavior);
    }

    public static SwitchToBehavior getViewInstance() {
        return new WebSwitchToView();
    }

    public abstract void execute();
}
