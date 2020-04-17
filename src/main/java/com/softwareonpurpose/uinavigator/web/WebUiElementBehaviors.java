package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetElementBehavior;
import com.softwareonpurpose.uinavigator.GetListBehavior;
import com.softwareonpurpose.uinavigator.GetTextBehavior;
import com.softwareonpurpose.uinavigator.SetElementBehavior;
import org.openqa.selenium.By;

import java.util.Collection;

public class WebUiElementBehaviors {
    private static final WebGetElementBehavior defaultParentLocator =
            WebGetElementByLocator.getInstance(new By.ByTagName("body"));
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
            By locator,
            String attribute, String attributeValue,
            Integer ordinal,
            WebGetElementBehavior getParent) {
        SetElementBehavior setElementBehavior;
        GetTextBehavior getTextBehavior;
        boolean isBodyTag = new By.ByTagName("body").equals(locator);
        WebGetElementBehavior webGetParent = getParent != null ? getParent : isBodyTag ? null : defaultParentLocator;
        WebGetElementBehavior getBehavior =
                WebGetElementProvider.getInstance(
                        locator, attribute, attributeValue, ordinal, webGetParent);
        GetListBehavior getListBehavior =
                WebGetListProvider.getInstance(
                        locator, attribute, attributeValue, ordinal, webGetParent);
        if (new By.ByTagName("select").equals(locator)) {
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
