package com.softwareonpurpose.uinavigator.web;
/*
  Copyright 2020 Craig A. Stockton
  <p/>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p/>
  http://www.apache.org/licenses/LICENSE-2.0
  <p/>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
import com.google.gson.Gson;
import com.softwareonpurpose.uinavigator.GetTextBehavior;
import com.softwareonpurpose.uinavigator.SetElementBehavior;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class WebUiElementBehaviors {
    private static boolean isLoggingSuppressed;
    private final WebGetElementBehavior getElement;
    private transient final String description;
    private transient final WebGetListBehavior getList;
    private transient final SetElementBehavior setElement;
    private transient final GetTextBehavior getText;
    private transient final WebGetAttributeBehavior getAttribute;
    private transient final WebIsDisplayedBehavior isDisplayed;
    private transient final WebSetAttributeBehavior setAttribute;
    private transient final WebSwitchToBehavior switchTo;
    private transient StateBehavior isActive;
    private transient StateBehavior isSelected;

    private WebUiElementBehaviors(
            String description, WebGetElementBehavior getElement,
            WebGetListBehavior getList,
            SetElementBehavior setElement,
            GetTextBehavior getText, WebSwitchToBehavior switchTo) {
        this.description = description;
        this.getElement = getElement;
        this.getList = getList;
        this.setElement = setElement;
        this.getText = getText;
        this.getAttribute = WebGetAttributeBehavior.getInstance(getElement);
        this.isDisplayed = WebIsDisplayedBehavior.getInstance(getElement);
        this.setAttribute = WebSetAttributeBehavior.getInstance(getElement);
        this.switchTo = switchTo;
    }

    static WebUiElementBehaviors getInstanceByLocator(String description, String locatorType, String locatorValue) {
        WebGetElementBehavior getElement = WebGetElementByLocator.getInstance(locatorType, locatorValue);
        WebGetListBehavior getList = WebGetListByLocator.getInstance(locatorType, locatorValue);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttribute(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue);
        WebGetListBehavior getList =
                WebGetListByLocatorAttribute.getInstance(locatorType, locatorValue, attribute, attributeValue);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    static WebUiElementBehaviors getInstanceByLocatorOrdinal(
            String description, String locatorType, String locatorValue, Integer ordinal) {
        WebGetElementBehavior getElement = WebGetElementByLocatorOrdinal.getInstance(locatorType, locatorValue, ordinal);
        WebGetListBehavior getList = WebGetListByLocatorOrdinal.getInstance(locatorType, locatorValue, ordinal);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    static WebUiElementBehaviors getInstanceByLocatorParent(
            String description, String locatorType, String locatorValue, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement = WebGetElementByLocatorParent.getInstance(locatorType, locatorValue, getParent);
        WebGetListBehavior getList = WebGetListByLocatorParent.getInstance(locatorType, locatorValue, getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeOrdinal(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeOrdinal.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinal.getInstance(locatorType, locatorValue, attribute, attributeValue, ordinal);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeParent.getInstance(locatorType, locatorValue, attribute, attributeValue, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeParent.getInstance(locatorType, locatorValue, attribute, attributeValue, getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    static WebUiElementBehaviors getInstanceByLocatorOrdinalParent(
            String description, String locatorType, String locatorValue,
            Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(locatorType, locatorValue, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorOrdinalParent.getInstance(locatorType, locatorValue, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeOrdinalParent(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeOrdinalParent.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinalParent.getInstance(
                        locatorType, locatorValue, attribute, attributeValue, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locatorType, locatorValue, getElement);
        GetTextBehavior getText = getGetTextBehavior(locatorType, locatorValue, getElement);
        WebSwitchToBehavior switchTo = getSwitchToBehavior(locatorType, locatorValue, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText, switchTo);
    }

    private static WebSwitchToBehavior getSwitchToBehavior(String locatorType, String locatorValue, WebGetElementBehavior getElement) {
        if (UiLocatorType.TAG.equals(locatorType) && "iframe".equals(locatorValue)) {
            return SwitchToFrame.getInstance(getElement);
        } else {
            return SwitchToView.getInstance();
        }
    }

    private static GetTextBehavior getGetTextBehavior(
            String locatorType, String locatorValue, WebGetElementBehavior getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return WebGetTextSelectBehavior.getInstance(getBehavior);
        } else {
            return WebGetTextDefaultBehavior.getInstance(getBehavior);
        }
    }

    private static SetElementBehavior getSetBehavior(
            String locatorType, String locatorValue, WebGetElementBehavior getBehavior) {
        if (UiLocatorType.TAG.equals(locatorType) && "select".equals(locatorValue)) {
            return WebSetSelectBehavior.getInstance(getBehavior);
        } else {
            return WebSetDefaultBehavior.getInstance(getBehavior);
        }
    }

    public static boolean isLoggingSuppressed() {
        return isLoggingSuppressed;
    }

    public static void suppressLogging(boolean suppressLogging) {
        isLoggingSuppressed = suppressLogging;
    }

    Object get() {
        return getElement.execute();
    }

    Collection<WebUiElement> getList() {
        return getList.execute();
    }

    String getText() {
        return getText.execute();
    }

    void set(String value) {
        value = value == null ? "" : value;
        if (!isLoggingSuppressed) {
            getLogger().info(String.format(getIndentation() + "Set %s to \"%s\"", getDescription(), value));
        }
        String message_unableToSet = "BLOCKED: Unable to set %s to \"%s\" using element hierarchy %s";
        try {
            setElement.execute(value);
        } catch (WebDriverException e) {
            final String errorMessage = String.format(message_unableToSet, getDescription(), value, this.toString());
            reportException(e, errorMessage);
        }
    }

    public String getAttribute(String attribute) {
        return getAttribute.execute(attribute);
    }

    boolean isDisplayed() {
        return isDisplayed.execute();
    }

    void setActiveBehavior(String attribute, String value) {
        isActive = StateBehavior.getInstance(getElement, attribute, value);
    }

    void setSelectedBehavior(String attribute, String value) {
        isSelected = StateBehavior.getInstance(getElement, attribute, value);
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

    private void reportException(Exception e, String errorMessage) {
        getLogger().error(errorMessage);
        e.printStackTrace();
        throw new WebDriverException(errorMessage);
    }

    void click() {
        if (!isLoggingSuppressed) {
            getLogger().info(String.format(getIndentation() + "Click %s", getDescription()));
        }
        WebElement element = getElement.execute();
        final String message = "BLOCKED: Unable to click %s using hierarchy %s";
        final String errorMessage = String.format(message, getDescription(), getElement.toString());
        if (element != null && !"".equals(element.getTagName())) {
            try {
                element.click();
            } catch (WebDriverException | NullPointerException e) {
                reportException(e, errorMessage);
            }
        } else {
            if (!isLoggingSuppressed) {
                getLogger().error(errorMessage);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean isSelected() {
        return isSelected.execute();
    }

    boolean isActive() {
        return isActive.execute();
    }

    public String getSrc() {
        return getAttribute.execute("src");
    }

    void setAttribute(String attribute, String value) {
        setAttribute.execute(attribute, value);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public WebGetElementBehavior getBehavior() {
        return getElement;
    }

    public void switchTo() {
        switchTo.execute();
    }
}
