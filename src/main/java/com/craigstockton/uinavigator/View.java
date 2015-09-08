package com.craigstockton.uinavigator;

public abstract class View {

    protected final HtmlElement viewElement;
    private final String viewUri;

    protected View(String viewUri, HtmlElement viewElement) {
        this.viewUri = viewUri;
        this.viewElement = viewElement;
    }

    protected abstract boolean confirmElementStates();

    protected void navTo() {
        BrowserHost.getInstance().load(viewUri);
    }
}
