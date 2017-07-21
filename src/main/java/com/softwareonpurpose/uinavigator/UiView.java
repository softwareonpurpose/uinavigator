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

    protected void load(String relativeUri) {
        relativeUri = relativeUri.substring(0, 1).equals("?") ? relativeUri : String.format("/%s", relativeUri);
        String explicitUri = String.format("%s%s", viewUri, relativeUri);
        UiHost.getInstance().load(explicitUri);
    }

    protected UiElement getElement() {
        return viewElement;
    }
}
