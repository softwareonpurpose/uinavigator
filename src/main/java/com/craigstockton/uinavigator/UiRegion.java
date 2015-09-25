package com.craigstockton.uinavigator;

import org.apache.logging.log4j.LogManager;

public abstract class UiRegion {

    private final UiElement regionElement;

    protected UiRegion(String description, UiElement regionElement) {
        LogManager.getLogger(this.getClass()).info(String.format("In %s...", description));
        this.regionElement = regionElement;
    }

    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    protected UiElement getElement() {
        return regionElement;
    }
}
