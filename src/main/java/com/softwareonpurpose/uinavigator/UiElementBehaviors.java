package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.*;

public class UiElementBehaviors {
    private final String type;
    private final GetElementBehavior getElement;
    private final SetElementBehavior setElement;
    private final GetTextBehavior getText;

    private UiElementBehaviors(String type, GetElementBehavior getElement, SetElementBehavior setElement, GetTextBehavior getText) {
        this.type = type;
        this.getElement = getElement;
        this.setElement = setElement;
        this.getText = getText;
    }

    public static UiElementBehaviors getInstance(String locatorType, String locatorValue) {
        String type;
        SetElementBehavior setElementBehavior;
        GetTextBehavior getTextBehavior;
        GetElementBehavior getElementBehavior;
        switch (WebUiHost.getInstance().getDriverName()) {
            case "chrome":
            case "firefox":
            default:
                type = "selenium";
                WebGetElementBehavior getBehavior = WebGetElementProvider.getInstance(locatorType, locatorValue);
                WebGetListBehavior getListBehavior = WebGetListByLocatorOnly.getInstance(locatorType, locatorValue);
                if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
                    setElementBehavior = WebSetSelectBehavior.getInstance(getBehavior);
                    getTextBehavior = WebGetTextSelectBehavior.getInstance(getBehavior);
                } else {
                    setElementBehavior = WebSetDefaultBehavior.getInstance(getBehavior);
                    getTextBehavior = WebGetTextDefaultBehavior.getInstance(getBehavior);
                }
                getElementBehavior = getBehavior;
        }
        return new UiElementBehaviors(type, getElementBehavior, setElementBehavior, getTextBehavior);
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return getText.execute();
    }

    public Object get() {
        return getElement.execute();
    }

    public void set(String value) {
        setElement.execute(value);
    }
}
