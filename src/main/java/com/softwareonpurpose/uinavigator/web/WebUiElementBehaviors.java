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
import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class WebUiElementBehaviors extends UiElementBehaviors {
    private static boolean isLoggingSuppressed;
    private final WebGetElementBehavior getElement;
    private transient final String description;
    private transient final WebGetListBehavior getList;
    private transient final SetElementBehavior setElement;
    private transient final GetTextBehavior getText;
    private transient final GetAttributeBehavior getAttribute;
    private transient final WebIsDisplayedBehavior isDisplayed;
    private transient final WebSetAttributeBehavior setAttribute;
    private transient final SwitchToBehavior switchTo;
    private transient final ClickBehavior click;
    private transient UiStateBehavior isActive;
    private transient UiStateBehavior isSelected;

    private WebUiElementBehaviors(
            String description, WebGetElementBehavior getElement,
            WebGetListBehavior getList,
            SetElementBehavior setElement,
            GetTextBehavior getText, SwitchToBehavior switchTo) {
        super(description, getElement, getList, getText, setElement, switchTo);
        this.description = description;
        this.getElement = getElement;
        this.getList = getList;
        this.setElement = setElement;
        this.getText = getText;
        this.getAttribute = GetAttributeBehavior.getInstance(getElement);
        this.isDisplayed = WebIsDisplayedBehavior.getInstance(getElement);
        this.setAttribute = WebSetAttributeBehavior.getInstance(getElement);
        this.switchTo = switchTo;
        this.click = ClickBehavior.getInstance(this.description, getElement);
    }

    public static boolean isLoggingSuppressed() {
        return isLoggingSuppressed;
    }

    public static void suppressLogging(boolean suppressLogging) {
        isLoggingSuppressed = suppressLogging;
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
        } catch (WebDriverException e) {
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
        isActive = WebStateBehavior.getInstance(getElement, attribute, value);
    }

    public void setSelectedBehavior(String attribute, String value) {
        isSelected = WebStateBehavior.getInstance(getElement, attribute, value);
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
        click.execute();
    }

    public boolean isSelected() {
        return isSelected.execute();
    }

    public boolean isActive() {
        return isActive.execute();
    }

    public String getSrc() {
        return getAttribute.execute("src");
    }

    public void setAttribute(String attribute, String value) {
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
