package com.craigstockton.uinavigator;

public abstract class Region {

    private final Element regionElement;

    private Region(Element regionElement) {
        this.regionElement = regionElement;
    }

    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }
}
