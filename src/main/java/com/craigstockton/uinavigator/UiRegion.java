package com.craigstockton.uinavigator;

public abstract class UiRegion {

    private final String description;
    private final UiElement regionElement;

    protected UiRegion(String description, UiElement regionElement) {
        this.description = description;
        this.regionElement = regionElement;
    }

    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    protected UiElement getElement() {
        return regionElement;
    }
}
