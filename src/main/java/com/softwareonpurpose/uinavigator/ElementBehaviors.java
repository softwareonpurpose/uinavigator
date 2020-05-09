package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import com.softwareonpurpose.uinavigator.web.*;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementBehaviors {
    private transient static boolean isLoggingSuppressed = false;
    private final String description;
    private final UiGetElement getElement;
    private transient final UiGetElementList getList;
    private transient final ElementGetText getText;
    private transient final ElementSet setElement;
    private transient final ElementGetAttribute getAttribute;
    private transient final ElementState isDisplayed;
    private transient final ElementClick click;
    private transient final ElementAttributeSet setAttribute;
    private transient final UiSwitchTo switchTo;
    private transient ElementState isActive;
    private transient ElementState isSelected;

    protected ElementBehaviors(String description, UiGetElement getElement, UiGetElementList getList, ElementGetText getText, ElementSet setElement, UiSwitchTo switchTo) {
        this.description = description;
        this.getElement = getElement;
        this.getList = getList;
        this.getText = getText;
        this.setElement = setElement;
        this.getAttribute = ElementGetAttribute.getInstance(getElement);
        this.isDisplayed = ElementState.getIsDisplayedInstance(getElement);
        this.switchTo = switchTo;
        this.click = ElementClick.getInstance(description, getElement);
        this.setAttribute = ElementAttributeSet.getInstance(getElement);
    }

    public static ElementBehaviors getInstanceByLocator(String description, String locatorType, String locatorValue) {
        UiGetElement getBehavior = WebUiGetElementByLocator.getInstance(description, locatorType, locatorValue);
        UiGetElementList getList = WebUiGetElementListByLocator.getInstance(description, locatorType, locatorValue);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static ElementBehaviors getInstanceByLocatorAttribute(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue) {
        WebUiGetElement getBehavior =
                WebUiGetElementByLocatorAttribute.getInstance(description, locatorType, locatorValue, attribute, attributeValue);
        WebUiGetElementList getList =
                WebUiGetElementListByLocatorAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static ElementBehaviors getInstanceByLocatorOrdinal(
            String description, String locatorType, String locatorValue, Integer ordinal) {
        WebUiGetElement getBehavior = WebUiGetElementByLocatorOrdinal.getInstance(description, locatorType, locatorValue, ordinal);
        WebUiGetElementList getList = WebUiGetElementListByLocatorOrdinal.getInstance(locatorType, locatorValue, ordinal);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static ElementBehaviors getInstanceByLocatorParent(
            String description, String locatorType, String locatorValue, UiGetElement getParent) {
        WebUiGetElement getBehavior = WebUiGetElementByLocatorParent.getInstance(description, locatorType, locatorValue, (WebUiGetElement) getParent);
        WebUiGetElementList getList = WebUiGetElementListByLocatorParent.getInstance(locatorType, locatorValue, (WebUiGetElement) getParent);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static ElementBehaviors getInstanceByLocatorAttributeOrdinal(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        WebUiGetElement getBehavior =
                WebUiGetElementByLocatorAttributeOrdinal.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal);
        WebUiGetElementList getList =
                WebUiGetElementListByLocatorAttributeOrdinal.getInstance(locatorType, locatorValue, attribute, attributeValue, ordinal);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static ElementBehaviors getInstanceByLocatorAttributeParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, WebUiGetElement getParent) {
        WebUiGetElement getBehavior =
                WebUiGetElementByLocatorAttributeParent.getInstance(description, locatorType, locatorValue, attribute, attributeValue, getParent);
        WebUiGetElementList getList =
                WebUiGetElementListByLocatorAttributeParent.getInstance(locatorType, locatorValue, attribute, attributeValue, getParent);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static ElementBehaviors getInstanceByLocatorOrdinalParent(
            String description, String locatorType, String locatorValue,
            Integer ordinal, WebUiGetElement getParent) {
        WebUiGetElement getBehavior =
                WebUiGetElementByLocatorOrdinalParent.getInstance(description, locatorType, locatorValue, ordinal, getParent);
        WebUiGetElementList getList =
                WebUiGetElementListByLocatorOrdinalParent.getInstance(locatorType, locatorValue, ordinal, getParent);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    public static ElementBehaviors getInstanceByLocatorAttributeOrdinalParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebUiGetElement getParent) {
        WebUiGetElement getBehavior =
                WebUiGetElementByLocatorAttributeOrdinalParent.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal, getParent);
        WebUiGetElementList getList =
                WebUiGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, getParent);
        ElementSet set = getSetBehavior(locatorType, locatorValue, getBehavior);
        ElementGetText getText = getGetTextBehavior(locatorType, locatorValue, getBehavior);
        UiSwitchTo switchTo = getSwitchToBehavior(locatorType, locatorValue, getBehavior);
        return new ElementBehaviors(description, getBehavior, getList, getText, set, switchTo);
    }

    private static UiSwitchTo getSwitchToBehavior(String locatorType, String locatorValue, UiGetElement getElement) {
        if (UiLocatorType.TAG.equals(locatorType) && "iframe".equals(locatorValue)) {
            return UiSwitchTo.getFrameInstance(getElement);
        } else {
            return UiSwitchTo.getViewInstance();
        }
    }

    private static ElementGetText getGetTextBehavior(
            String locatorType, String locatorValue, UiGetElement getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return ElementGetText.getSelectInstance(getBehavior);
        } else {
            return ElementGetText.getInstance(getBehavior);
        }
    }

    private static ElementSet getSetBehavior(
            String locatorType, String locatorValue, UiGetElement getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return ElementSet.getSelectInstance(getBehavior);
        } else {
            return ElementSet.getInstance(getBehavior);
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
        isActive = ElementState.getIsActiveInstance(getElement, attribute, value);
    }

    public void setSelectedBehavior(String attribute, String value) {
        isSelected = ElementState.getIsActiveInstance(getElement, attribute, value);
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

    public UiGetElement getBehavior() {
        return getElement;
    }

    public void switchTo() {
        switchTo.execute();
    }
}
