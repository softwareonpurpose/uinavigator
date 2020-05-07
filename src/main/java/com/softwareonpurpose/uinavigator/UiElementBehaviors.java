package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import com.softwareonpurpose.uinavigator.web.*;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class UiElementBehaviors {
    private static boolean isLoggingSuppressed = false;
    private final GetElementBehavior getElement;
    private transient final GetListBehavior getList;
    private transient final GetTextBehavior getText;
    private transient final SetElementBehavior setElement;
    private transient final GetAttributeBehavior getAttribute;
    private transient final IsDisplayedBehavior isDisplayed;
    private transient final String description;
    private transient final ClickBehavior click;
    private transient final SetAttributeBehavior setAttribute;
    private transient final SwitchToBehavior switchTo;
    private transient UiStateBehavior isActive;
    private transient UiStateBehavior isSelected;

    protected UiElementBehaviors(String description, GetElementBehavior getElement, GetListBehavior getList, GetTextBehavior getText, SetElementBehavior setElement, SwitchToBehavior switchTo) {
        this.description = description;
        this.getElement = getElement;
        this.getList = getList;
        this.getText = getText;
        this.setElement = setElement;
        this.getAttribute = GetAttributeBehavior.getInstance(getElement);
        this.isDisplayed = IsDisplayedBehavior.getInstance(getElement);
        this.switchTo = switchTo;
        this.click = ClickBehavior.getInstance(description, getElement);
        this.setAttribute = SetAttributeBehavior.getInstance(getElement);
    }

    public static UiElementBehaviors getInstanceByLocator(String description, String locatorType, String locatorValue) {
        GetElementBehavior getBehavior = WebGetElementByLocator.getInstance(locatorType, locatorValue);
        GetListBehavior getList = WebGetListByLocator.getInstance(locatorType, locatorValue);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static UiElementBehaviors getInstanceByLocatorAttribute(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue) {
        WebGetElementBehavior getBehavior =
                WebGetElementByLocatorAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue);
        WebGetListBehavior getList =
                WebGetListByLocatorAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static UiElementBehaviors getInstanceByLocatorOrdinal(
            String description, String locatorType, String locatorValue, Integer ordinal) {
        WebGetElementBehavior getBehavior = WebGetElementByLocatorOrdinal.getInstance(locatorType, locatorValue, ordinal);
        WebGetListBehavior getList = WebGetListByLocatorOrdinal.getInstance(locatorType, locatorValue, ordinal);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static UiElementBehaviors getInstanceByLocatorParent(
            String description, String locatorType, String locatorValue, GetElementBehavior getParent) {
        WebGetElementBehavior getBehavior = WebGetElementByLocatorParent.getInstance(locatorType, locatorValue, (WebGetElementBehavior) getParent);
        WebGetListBehavior getList = WebGetListByLocatorParent.getInstance(locatorType, locatorValue, (WebGetElementBehavior) getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeOrdinal(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        WebGetElementBehavior getBehavior =
                WebGetElementByLocatorAttributeOrdinal.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinal.getInstance(locatorType, locatorValue, attribute, attributeValue, ordinal);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, WebGetElementBehavior getParent) {
        WebGetElementBehavior getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(locatorType, locatorValue, attribute, attributeValue, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeParent.getInstance(locatorType, locatorValue, attribute, attributeValue, getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static UiElementBehaviors getInstanceByLocatorOrdinalParent(
            String description, String locatorType, String locatorValue,
            Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getBehavior =
                WebGetElementByLocatorOrdinalParent.getInstance(locatorType, locatorValue, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorOrdinalParent.getInstance(locatorType, locatorValue, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeOrdinalParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getBehavior =
                WebGetElementByLocatorAttributeOrdinalParent.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinalParent.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getBehavior);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        SwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    private static SwitchToBehavior getSwitchToBehavior(String locatorType, String locatorValue, GetElementBehavior getElement) {
        if (UiLocatorType.TAG.equals(locatorType) && "iframe".equals(locatorValue)) {
            return SwitchToBehavior.getFrameInstance(getElement);
        } else {
            return SwitchToBehavior.getViewInstance();
        }
    }

    private static GetTextBehavior getGetTextBehavior(
            String locatorType, String locatorValue, GetElementBehavior getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return GetTextBehavior.getSelectInstance(getBehavior);
        } else {
            return GetTextBehavior.getInstance(getBehavior);
        }
    }

    private static SetElementBehavior getSetBehavior(
            String locatorType, String locatorValue, GetElementBehavior getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return SetElementBehavior.getSelectInstance(getBehavior);
        } else {
            return SetElementBehavior.getInstance(getBehavior);
        }
    }

    public static boolean isLoggingSuppressed() {
        return isLoggingSuppressed;
    }

    public static void suppressLogging(boolean suppressLogging) {
        isLoggingSuppressed = suppressLogging;
    }

    public static void reportException(Exception e, String errorMessage) {
        final String root = "";
        LoggerFactory.getLogger(root).error(errorMessage);
        e.printStackTrace();
        throw new WebDriverException(errorMessage);
    }

    public Object get() {
        return getElement.execute();
    }

    public Collection<UiElement> getList() {
        return getList.execute();
    }

    public String getText() {
        return getText.execute();
    }

    public void set(String value) {
        value = value == null ? "" : value;
        if (!isLoggingSuppressed) {
            getLogger().info(String.format(getIndentation() + "Set %s to \"%s\"", getDescription(), value));
        }
        String message_unableToSet = "BLOCKED: Unable to set %s to \"%s\" using element hierarchy %s";
        try {
            setElement.execute(value);
        } catch (Exception e) {
            final String errorMessage = String.format(message_unableToSet, getDescription(), value, this.toString());
            reportException(e, errorMessage);
        }
    }

    public String getAttribute(String attribute) {
        return getAttribute.execute(attribute);
    }

    public boolean isDisplayed() {
        return isDisplayed.execute();
    }

    public void setActiveBehavior(String attribute, String value) {
        isActive = UiStateBehavior.getInstance(getElement, attribute, value);
    }

    public void setSelectedBehavior(String attribute, String value) {
        isSelected = UiStateBehavior.getInstance(getElement, attribute, value);
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger("");
    }

    private String getIndentation() {
        return new String(new char[4]).replace('\0', ' ');
    }

    private String getDescription() {
        return description;
    }

    public void click() {
        if (!isLoggingSuppressed) {
            getLogger().info(String.format(getIndentation() + "Click %s", getDescription()));
        }
        click.execute();
    }

    public boolean isSelected() {
        return isSelected.execute();
    }

    public boolean isActive() {
        return isActive.execute();
    }

    public void setAttribute(String attribute, String value) {
        setAttribute.execute(attribute, value);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public GetElementBehavior getBehavior() {
        return getElement;
    }

    public void switchTo() {
        switchTo.execute();
    }
}
