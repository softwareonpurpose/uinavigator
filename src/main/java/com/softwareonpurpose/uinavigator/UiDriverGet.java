package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.ChromeUiDriverService;
import org.slf4j.LoggerFactory;

public class UiDriverGet {
    private static UiDriverService instantiation;
    private Object driver;

    private UiDriverGet(UiDriverService instantiation) {
        if (UiDriverGet.instantiation == null) {
            UiDriverGet.instantiation = instantiation == null ? ChromeUiDriverService.getInstance() : instantiation;
        }
    }

    public static UiDriverGet getInstance() {
        return new UiDriverGet(null);
    }

    public static UiDriverGet getInstance(UiDriverService instantiation) {
        setInstantiation(instantiation);
        return getInstance();
    }

    public static void setInstantiation(UiDriverService instantiation) {
        UiDriverGet.instantiation = instantiation;
    }

    public void quit() {
        UiDriverQuit.getInstance(this).execute();
    }

    public Object execute() {
        if (driver == null) {
            try {
                driver = instantiation.getDriver();
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
