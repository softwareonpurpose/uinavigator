package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.ChromeDriverInstantiation;
import org.slf4j.LoggerFactory;

public class UiDriverGet {
    private static UiDriverInstantiation instantiation;
    private Object driver;

    private UiDriverGet(UiDriverInstantiation instantiation) {
        if (UiDriverGet.instantiation == null) {
            UiDriverGet.instantiation = instantiation == null ? ChromeDriverInstantiation.getInstance() : instantiation;
        }
    }

    public static UiDriverGet getInstance() {
        return new UiDriverGet(null);
    }

    public static UiDriverGet getInstance(UiDriverInstantiation instantiation) {
        setInstantiation(instantiation);
        return getInstance();
    }

    public static void setInstantiation(UiDriverInstantiation instantiation) {
        UiDriverGet.instantiation = instantiation;
    }

    public void quit() {
        UiDriverQuit.getInstance(this).execute();
    }

    public Object execute() {
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
