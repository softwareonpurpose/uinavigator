package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;

import java.util.Collection;

public class WebUiElementBehaviors {
    private static final WebGetElementBehavior defaultParentLocator =
            WebGetElementByLocator.getInstance(UiLocatorType.TAG, "body");
    private final GetElementBehavior getElement;
    private final GetListBehavior getList;
    private final SetElementBehavior setElement;
    private final GetTextBehavior getText;

    private WebUiElementBehaviors(
            GetElementBehavior getElement,
            GetListBehavior getList,
            SetElementBehavior setElement,
            GetTextBehavior getText
    ) {
        this.getElement = getElement;
        this.getList = getList;
        this.setElement = setElement;
        this.getText = getText;
    }

    public static WebUiElementBehaviors getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue,
            Integer ordinal,
            WebGetElementBehavior getParent) {
        SetElementBehavior setElementBehavior;
        GetTextBehavior getTextBehavior;
        boolean isBodyTag = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue));
        WebGetElementBehavior webGetParent = getParent != null ? getParent : isBodyTag ? null : defaultParentLocator;
        WebGetElementBehavior getBehavior =
                WebGetElementProvider.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, webGetParent);
        GetListBehavior getListBehavior =
                WebGetListBehavior.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, webGetParent);
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            setElementBehavior = WebSetSelectBehavior.getInstance(getBehavior);
            getTextBehavior = WebGetTextSelectBehavior.getInstance(getBehavior);
        } else {
            setElementBehavior = WebSetDefaultBehavior.getInstance(getBehavior);
            getTextBehavior = WebGetTextDefaultBehavior.getInstance(getBehavior);
        }
        return new WebUiElementBehaviors(getBehavior, getListBehavior, setElementBehavior, getTextBehavior);
    }

    Object get() {
        return getElement.execute();
    }

    Collection getList() {
        return getList.execute();
    }

    String getText() {
        return getText.execute();
    }

    void set(String value) {
        setElement.execute(value);
    }
}
