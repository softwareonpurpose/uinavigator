package com.craigstockton.uinavigator;

public abstract class View {

    private final Element viewElement;
    private final String viewUri;

    protected View(String viewUri, Element viewElement) {
        this.viewUri = viewUri;
        this.viewElement = viewElement;
    }

    protected abstract boolean confirmElementStates();

    protected void load() {
        Host.getInstance().load(viewUri);
    }

    protected Element getViewElement() {
        return viewElement;
    }
}
