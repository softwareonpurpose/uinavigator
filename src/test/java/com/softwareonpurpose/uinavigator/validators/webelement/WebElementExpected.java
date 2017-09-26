package com.softwareonpurpose.uinavigator.validators.webelement;

public class WebElementExpected {
    private final String href;
    private final String tag;
    private final String id;
    private final String classes;
    private final String text;

    private WebElementExpected(String tag, String id, String classes, String href, String text) {
        this.tag = tag;
        this.id = id;
        this.classes = classes;
        this.href = href;
        this.text = text;
    }

    public static WebElementExpected getInstance(String tag, String id, String classes, String href, String text) {
        return new WebElementExpected(tag, id, classes, href, text);
    }

    String getId() {
        return id == null ? "" : id;
    }

    String getClasses() {
        return classes == null ? "" : classes;
    }

    String getTag() {
        return tag;
    }

    String getHref() {
        return href;
    }
}
