package com.craigstockton.uinavigator;

public abstract class UiRegion {

    private final UiElement regionElement;

    protected UiRegion(UiElement regionElement) {
        this.regionElement = regionElement;
    }

    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    protected UiElement getElement() {
        return regionElement;
    }
}
