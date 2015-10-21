package com.softwareonpurpose.uinavigator;

public abstract class UiView {

    private final UiElement viewElement;
    private final String viewUri;

    protected UiView(String viewUri, UiElement viewElement) {
        this.viewUri = viewUri;
        this.viewElement = viewElement;
    }

    protected abstract boolean confirmElementStates();

    protected void load() {
        UiHost.getInstance().load(viewUri);
    }

    protected UiElement getElement() {
        return viewElement;
    }
}
