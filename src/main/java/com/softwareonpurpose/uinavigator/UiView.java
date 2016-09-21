package com.softwareonpurpose.uinavigator;

public abstract class UiView {

    private final UiElement viewElement;
    private final String VIEW_URI;

    protected UiView(String viewUri, UiElement viewElement) {
        this.VIEW_URI = viewUri;
        this.viewElement = viewElement;
    }

    protected abstract boolean confirmElementStates();

    protected void load() {
        UiHost.getInstance().load(VIEW_URI);
    }

    protected void load(String relativeUri) {
        String explicitUri = String.format("%s/%s", VIEW_URI, relativeUri);
        UiHost.getInstance().load(explicitUri);
    }

    protected UiElement getElement() {
        return viewElement;
    }
}
