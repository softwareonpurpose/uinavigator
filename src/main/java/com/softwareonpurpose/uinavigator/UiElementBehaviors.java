package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import com.softwareonpurpose.uinavigator.web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UiElementBehaviors {
    private transient static boolean isLoggingSuppressed = false;
    private transient final UiHost host;
    private final String description;
    private final UiElementGet getElement;
    private transient final UiElementGetList getList;
    private transient final UiElementGetText getText;
    private transient final UiElementSet setElement;
    private transient final UiElementGetAttribute getAttribute;
    private transient final UiElementClick click;
    private transient UiElementState isActive;
    private transient UiElementState isSelected;

    protected UiElementBehaviors(String description, UiElementGet getElement, UiElementGetList getList, UiElementGetText getText, UiElementSet setElement, UiHost host) {
        this.host = host;
        this.description = description;
        this.getElement = getElement;
        this.getList = getList;
        this.getText = getText;
        this.setElement = setElement;
        this.getAttribute = UiElementGetAttribute.getInstance(getElement);
        this.click = UiElementClick.getInstance(description, getElement);
    }

    public static UiElementBehaviors getInstanceByLocator(String description, UiLocatorType locatorType, String locatorValue, UiHost host) {
        UiElementGet getBehavior = WebElementGetByLocator.getInstance(description, locatorType, locatorValue, host);
        UiElementGetList getList = WebGetElementListByLocator.getInstance(description, locatorType, locatorValue, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    public static UiElementBehaviors getInstanceByLocatorAttribute(
            String description, UiLocatorType locatorType, String locatorValue, String attribute, String attributeValue, UiHost host) {
        WebElementGet getBehavior =
                WebElementGetByLocatorAttribute.getInstance(description, locatorType, locatorValue, attribute, attributeValue, host);
        UiElementGetList getList =
                WebGetElementListByLocatorAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    public static UiElementBehaviors getInstanceByLocatorOrdinal(
            String description, UiLocatorType locatorType, String locatorValue, Integer ordinal, UiHost host) {
        WebElementGet getBehavior = WebElementGetByLocatorOrdinal.getInstance(description, locatorType, locatorValue, ordinal, host);
        UiElementGetList getList = WebGetElementListByLocatorOrdinal.getInstance(locatorType, locatorValue, ordinal, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    public static UiElementBehaviors getInstanceByLocatorParent(
            String description, UiLocatorType locatorType, String locatorValue, UiElementGet getParent, UiHost host) {
        WebElementGet getBehavior = WebElementGetByLocatorParent.getInstance(description, locatorType, locatorValue, (WebElementGet) getParent, host);
        UiElementGetList getList = WebGetElementListByLocatorParent.getInstance(locatorType, locatorValue, getParent, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeOrdinal(
            String description, UiLocatorType locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiHost host) {
        WebElementGet getBehavior =
                WebElementGetByLocatorAttributeOrdinal.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal, host);
        UiElementGetList getList =
                WebGetElementListByLocatorAttributeOrdinal.getInstance(locatorType, locatorValue, attribute, attributeValue, ordinal, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeParent(
            String description, UiLocatorType locatorType, String locatorValue,
            String attribute, String attributeValue, WebElementGet getParent, UiHost host) {
        WebElementGet getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(description, locatorType, locatorValue, attribute, attributeValue, getParent, host);
        UiElementGetList getList =
                WebGetElementListByLocatorAttributeParent.getInstance(locatorType, locatorValue, attribute, attributeValue, getParent, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    public static UiElementBehaviors getInstanceByLocatorOrdinalParent(
            String description, UiLocatorType locatorType, String locatorValue,
            Integer ordinal, WebElementGet getParent, UiHost host) {
        WebElementGet getBehavior =
                WebElementGetByLocatorOrdinalParent.getInstance(description, locatorType, locatorValue, ordinal, getParent, host);
        UiElementGetList getList =
                WebGetElementListByLocatorOrdinalParent.getInstance(locatorType, locatorValue, ordinal, getParent, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeOrdinalParent(
            String description, UiLocatorType locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebElementGet getParent, UiHost host) {
        WebElementGet getBehavior =
                WebElementGetByLocatorAttributeOrdinalParent.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal, getParent, host);
        UiElementGetList getList =
                WebGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, getParent, host);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, host);
    }

    private static UiElementGetText getGetTextBehavior(
            UiLocatorType locatorType, String locatorValue, UiElementGet getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return UiElementGetText.getSelectInstance(getBehavior);
        } else {
            return UiElementGetText.getInstance(getBehavior);
        }
    }

    private static UiElementSet getSetBehavior(
            UiLocatorType locatorType, String locatorValue, UiElementGet getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return UiElementSet.getSelectInstance(getBehavior);
        } else {
            return UiElementSet.getInstance(getBehavior);
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
        throw new UiElementException(errorMessage);
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
            final String thisToString = this.toString();
            final String errorMessage = String.format(message_unableToSet, getDescription(), value, thisToString);
            reportException(e, errorMessage);
        }
    }

    public String getAttribute(String attribute) {
        return getAttribute.execute(attribute);
    }

    public boolean isDisplayed() {
        return host.waitUntilVisible(getElement);
    }

    public void setActiveBehavior(String attribute, String value) {
        isActive = UiElementState.getIsStateInstance(getElement, attribute, value);
    }

    public void setSelectedBehavior(String attribute, String value) {
        isSelected = UiElementState.getIsStateInstance(getElement, attribute, value);
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
        Object[] arguments = {getElement.execute(), attribute, value};
        host.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", arguments);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public UiElementGet getBehavior() {
        return getElement;
    }
}
