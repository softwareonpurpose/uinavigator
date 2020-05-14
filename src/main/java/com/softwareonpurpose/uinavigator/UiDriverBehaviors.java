package com.softwareonpurpose.uinavigator;

public class UiDriverBehaviors {
    private final UiDriverQuit quit;

    public UiDriverBehaviors(UiDriverQuit quit) {
        this.quit = WebDriverQuit.getInstance();
    }

    public void quit() {
        quit.execute();
    }
}
