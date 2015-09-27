package com.craigstockton.uinavigator;

public abstract class UiView {

    private final UiElement viewElement;
    private final String viewUri;

    public String getUri() {
        return viewUri;
    }

    protected UiView(String viewUri, UiElement viewElement) {
        this.viewUri = viewUri;
        this.viewElement = viewElement;
    }

    protected abstract boolean confirmElementStates();

    protected void load() {
        UiHost.getInstance().load(getUri());
    }

    protected UiElement getElement() {
        return viewElement;
    }
}
