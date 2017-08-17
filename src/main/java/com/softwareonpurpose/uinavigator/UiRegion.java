package com.softwareonpurpose.uinavigator;

import org.slf4j.LoggerFactory;

public abstract class UiRegion {

    private final UiElement regionElement;
    private static boolean suppressLogging;

    protected UiRegion(UiElement regionElement) {
        this.regionElement = regionElement;
        if (!suppressLogging)
            LoggerFactory.getLogger(this.getClass()).info(String.format("In %s...", getDescription()));
    }

    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    public static void suppressConstructionLogging(boolean suppress) {
        suppressLogging = suppress;
    }

    protected UiElement getElement() {
        return regionElement;
    }

    protected String getDescription() {
        return getElement().getDescription();
    }
}
