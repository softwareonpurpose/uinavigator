package com.craigstockton.uinavigator;

import org.apache.logging.log4j.LogManager;

public abstract class UiRegion {

    private final UiElement regionElement;

    protected UiRegion(UiElement regionElement) {
        this.regionElement = regionElement;
        LogManager.getLogger(this.getClass()).info(String.format("In %s...", getDescription()));
    }

    boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    protected UiElement getElement() {
        return regionElement;
    }

    protected String getDescription() {
        return getElement().getDescription();
    }
}
