package com.softwareonpurpose.uinavigator;

import org.slf4j.LoggerFactory;

public class UiDriverGet {
    private static Object driver;
    private static UiDriverInstantiation instantiation;
    private static UiDriverQuit termination;

    public static void setBehaviors(UiDriverInstantiation instantiation, UiDriverQuit termination) {
        UiDriverGet.instantiation = instantiation;
        UiDriverGet.termination = termination;
    }

    public static void quit() {
        termination.execute();
    }

    public static Object execute() {
        if (driver == null) {
            try {
                driver = instantiation.execute();
            } catch (NullPointerException e) {
                final String message =
                        "No driver instantiation available.  Initialize with UiDriverGet.setInstantiation";
                LoggerFactory.getLogger(UiDriverGet.class).error(message);
                e.printStackTrace();
            }
        }
        return driver;
    }
}
