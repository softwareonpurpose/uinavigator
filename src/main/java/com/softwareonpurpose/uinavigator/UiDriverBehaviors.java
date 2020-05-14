package com.softwareonpurpose.uinavigator;

public class UiDriverBehaviors {
    private final DriverQuit quit;

    public UiDriverBehaviors(DriverQuit quit) {
        this.quit = WebDriverQuit.getInstance();
    }

    public void quit() {
        quit.execute();
    }
}
