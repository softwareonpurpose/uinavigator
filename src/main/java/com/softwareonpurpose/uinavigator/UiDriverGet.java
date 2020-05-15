package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.ChromeDriverInstantiation;
import org.slf4j.LoggerFactory;

public class UiDriverGet {
    private static UiDriverInstantiation instantiation;
    private static UiDriverGet getDriver;
    private Object driver;

    private UiDriverGet(UiDriverInstantiation instantiation) {
        UiDriverGet.instantiation = instantiation == null ? ChromeDriverInstantiation.getInstance() : instantiation;
    }

    public static UiDriverGet getInstance() {
        if (getDriver == null) {
            getDriver = new UiDriverGet(null);
        }
        return getDriver;
    }

    public static UiDriverGet getInstance(UiDriverInstantiation instantiation) {
        setInstantiation(instantiation);
        return getInstance();
    }

    public static void setInstantiation(UiDriverInstantiation instantiation) {
        UiDriverGet.instantiation = instantiation;
    }

    public static void quit() {
        UiDriverQuit.getInstance(getInstance()).execute();
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
