package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import com.softwareonpurpose.uinavigator.web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UiElementBehaviors {
    private transient static boolean isLoggingSuppressed = false;
    private final String description;
    private final UiElementGet getElement;
    private transient final UiElementGetList getList;
    private transient final UiElementGetText getText;
    private transient final UiElementSet setElement;
    private transient final UiElementGetAttribute getAttribute;
    private transient final UiElementState isDisplayed;
    private transient final UiElementClick click;
    private transient final UiElementAttributeSet setAttribute;
    private transient final UiSwitchTo switchTo;
    transient final UiDriverGet getDriver;
    private transient UiElementState isActive;
    private transient UiElementState isSelected;

    protected UiElementBehaviors(String description, UiElementGet getElement, UiElementGetList getList, UiElementGetText getText, UiElementSet setElement, UiSwitchTo switchTo, UiDriverGet getDriver) {
        this.getDriver = getDriver;
        this.description = description;
        this.getElement = getElement;
        this.getList = getList;
        this.getText = getText;
        this.setElement = setElement;
        this.getAttribute = UiElementGetAttribute.getInstance(getElement);
        this.isDisplayed = UiElementState.getIsDisplayedInstance(getElement, getDriver);
        this.switchTo = switchTo;
        this.click = UiElementClick.getInstance(description, getElement);
        this.setAttribute = UiElementAttributeSet.getInstance(getElement, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocator(String description, String locatorType, String locatorValue) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        UiElementGet getBehavior = WebElementGetByLocator.getInstance(description, locatorType, locatorValue, getDriver);
        UiElementGetList getList = WebGetElementListByLocator.getInstance(description, locatorType, locatorValue, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocatorAttribute(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        WebElementGet getBehavior =
                WebElementGetByLocatorAttribute.getInstance(description, locatorType, locatorValue, attribute, attributeValue, getDriver);
        UiElementGetList getList =
                WebGetElementListByLocatorAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocatorOrdinal(
            String description, String locatorType, String locatorValue, Integer ordinal) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        WebElementGet getBehavior = WebElementGetByLocatorOrdinal.getInstance(description, locatorType, locatorValue, ordinal, getDriver);
        UiElementGetList getList = WebGetElementListByLocatorOrdinal.getInstance(locatorType, locatorValue, ordinal, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocatorParent(
            String description, String locatorType, String locatorValue, UiElementGet getParent) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        WebElementGet getBehavior = WebElementGetByLocatorParent.getInstance(description, locatorType, locatorValue, (WebElementGet) getParent, getDriver);
        UiElementGetList getList = WebGetElementListByLocatorParent.getInstance(locatorType, locatorValue, getParent, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeOrdinal(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        WebElementGet getBehavior =
                WebElementGetByLocatorAttributeOrdinal.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal, getDriver);
        UiElementGetList getList =
                WebGetElementListByLocatorAttributeOrdinal.getInstance(locatorType, locatorValue, attribute, attributeValue, ordinal, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, WebElementGet getParent) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        WebElementGet getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(description, locatorType, locatorValue, attribute, attributeValue, getParent, getDriver);
        UiElementGetList getList =
                WebGetElementListByLocatorAttributeParent.getInstance(locatorType, locatorValue, attribute, attributeValue, getParent, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocatorOrdinalParent(
            String description, String locatorType, String locatorValue,
            Integer ordinal, WebElementGet getParent) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        WebElementGet getBehavior =
                WebElementGetByLocatorOrdinalParent.getInstance(description, locatorType, locatorValue, ordinal, getParent, getDriver);
        UiElementGetList getList =
                WebGetElementListByLocatorOrdinalParent.getInstance(locatorType, locatorValue, ordinal, getParent, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    public static UiElementBehaviors getInstanceByLocatorAttributeOrdinalParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebElementGet getParent) {
        UiDriverGet getDriver = UiDriverGet.getInstance();
        WebElementGet getBehavior =
                WebElementGetByLocatorAttributeOrdinalParent.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal, getParent, getDriver);
        UiElementGetList getList =
                WebGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, getParent, getDriver);
        UiElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        UiElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior, getDriver);
        return new UiElementBehaviors(description, getBehavior, getList, getText, set, switchTo, getDriver);
    }

    private static UiSwitchTo getSwitchToBehavior(String locatorType, String locatorValue, UiElementGet getElement, UiDriverGet getDriver) {
        if (UiLocatorType.TAG.equals(locatorType) && "iframe".equals(locatorValue)) {
            return UiSwitchTo.getFrameInstance(getElement, getDriver);
        } else {
            return UiSwitchTo.getViewInstance(getDriver);
        }
    }

    private static UiElementGetText getGetTextBehavior(
            String locatorType, String locatorValue, UiElementGet getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return UiElementGetText.getSelectInstance(getBehavior);
        } else {
            return UiElementGetText.getInstance(getBehavior);
        }
    }

    private static UiElementSet getSetBehavior(
            String locatorType, String locatorValue, UiElementGet getBehavior) {
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
        return isDisplayed.execute();
    }

    public void setActiveBehavior(String attribute, String value) {
        isActive = UiElementState.getIsStateInstance(getElement, attribute, value, getDriver);
    }

    public void setSelectedBehavior(String attribute, String value) {
        isSelected = UiElementState.getIsStateInstance(getElement, attribute, value, getDriver);
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

    public UiElementGet getBehavior() {
        return getElement;
    }

    public void switchTo() {
        switchTo.execute();
    }
}
