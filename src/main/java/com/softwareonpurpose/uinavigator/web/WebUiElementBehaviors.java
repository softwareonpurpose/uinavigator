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
            WebGetElementBehavior getElement,
            WebGetListBehavior getList,
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
        WebGetListBehavior getListBehavior =
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

    static WebUiElementBehaviors getInstanceByLocator(By locator) {
        WebGetElementBehavior getElement = WebGetElementByLocator.getInstance(locator);
        WebGetListBehavior getList = WebGetListByLocator.getInstance(locator);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttribute(By locator, String attribute, String attributeValue) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttribute.getInstance(locator, attribute, attributeValue);
        WebGetListBehavior getList = WebGetListByLocatorAttribute.getInstance(locator, attribute, attributeValue);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorOrdinal(By locator, Integer ordinal) {
        WebGetElementBehavior getElement = WebGetElementByLocatorOrdinal.getInstance(locator, ordinal);
        WebGetListBehavior getList = WebGetListByLocatorOrdinal.getInstance(locator, ordinal);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorParent(By locator, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement = WebGetElementByLocatorParent.getInstance(locator, getParent);
        WebGetListBehavior getList = WebGetListByLocatorParent.getInstance(locator, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeOrdinal(
            By locator, String attribute, String attributeValue, Integer ordinal) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeParent(
            By locator, String attribute, String attributeValue, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorOrdinalParent(
            By locator, Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(locator, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorOrdinalParent.getInstance(locator, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeOrdinalParent(
            By locator, String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(getElement, getList, set, getText);
    }

    private static GetTextBehavior getGetTextBehavior(By locator, WebGetElementBehavior getBehavior) {
        if (new By.ByTagName("select").equals(locator)) {
            return WebGetTextSelectBehavior.getInstance(getBehavior);
        } else {
            return WebGetTextDefaultBehavior.getInstance(getBehavior);
        }
    }

    private static SetElementBehavior getSetBehavior(By locator, WebGetElementBehavior getBehavior) {
        if (new By.ByTagName("select").equals(locator)) {
            return WebSetSelectBehavior.getInstance(getBehavior);
        } else {
            return WebSetDefaultBehavior.getInstance(getBehavior);
        }
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
