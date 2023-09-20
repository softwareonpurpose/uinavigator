package com.softwareonpurpose.uinavigator;


import org.apache.logging.log4j.LogManager;

public abstract class UiRegion4 {
    private final UiElement4 regionElement;

    public UiRegion4(UiElement4 regionElement) {
        LogManager.getLogger("").info(String.format("In '%s' region ...", regionElement.getDescription()));
        this.regionElement = regionElement;
    }

    public boolean isDisplayed() {
        boolean isDisplayed = this.getElement().isDisplayed();
        return isDisplayed && confirmState();
    }

    private UiElement4 getElement() {
        return regionElement;
    }

    protected abstract boolean confirmState();
}
