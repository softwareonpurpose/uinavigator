package com.softwareonpurpose.uinavigator;
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
import com.softwareonpurpose.uinavigator.web.WebElementLocator;
import com.softwareonpurpose.uinavigator.web.WebUiGetElement;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UiElement {
    private final String description;
    private final ElementBehaviors behaviors;

    private UiElement(String description, ElementBehaviors behaviors) {
        this.description = description;
        this.behaviors = behaviors;
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocator(description, locatorType, locatorValue);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttribute(
                        description, locatorType, locatorValue, attribute, attributeValue);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        Integer ordinal) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorOrdinal(description, locatorType, locatorValue, ordinal);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        UiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorParent(description, locatorType, locatorValue, parent.getBehavior());
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue,
                                        Integer ordinal) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeOrdinal(description,
                        locatorType, locatorValue, attribute, attributeValue, ordinal);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue,
                                        UiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeParent(description,
                        locatorType, locatorValue, attribute, attributeValue, (WebUiGetElement) parent.getBehavior());
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        Integer ordinal,
                                        UiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorOrdinalParent(
                        description, locatorType, locatorValue, ordinal, (WebUiGetElement) parent.getBehavior());
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue,
                                        Integer ordinal,
                                        UiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(description,
                        locatorType, locatorValue, attribute, attributeValue, ordinal, (WebUiGetElement) parent.getBehavior());
        return new UiElement(description, behaviors);
    }

    public static void suppressLogging(boolean suppressLogging) {
        ElementBehaviors.suppressLogging(suppressLogging);
    }

    public static List<UiElement> getList(String description, String locatorType, String locatorValue, UiElement parent) {
        List<UiElement> elements = new ArrayList<>();
        WebElement parentElement = (WebElement) (parent.getBehavior()).execute();
        List<WebElement> webElements = parentElement != null ? parentElement
                .findElements(WebElementLocator.getInstance(locatorType, locatorValue)) : new ArrayList<>();
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal++) {
            String elementDescription = String.format("%s #%d", description, elementOrdinal);
            elements.add(UiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal, parent));
        }
        return elements;
    }

    public static boolean isLoggingSuppressed() {
        return ElementBehaviors.isLoggingSuppressed();
    }

    private UiGetElement getBehavior() {
        return behaviors.getBehavior();
    }

    public String getText() {
        return behaviors.getText();
    }

    public String getHref() {
        return behaviors.getAttribute("href");
    }

    public void set(String value) {
        behaviors.set(value);
    }

    public void click() {
        behaviors.click();
    }

    public boolean isActive() {
        return behaviors.isActive();
    }

    public boolean isSelected() {
        return behaviors.isSelected();
    }

    public boolean isDisplayed() {
        return behaviors.isDisplayed();
    }

    public String getAttribute(String attribute) {
        return behaviors.getAttribute(attribute);
    }

    public void setAttribute(String attribute, String value) {
        behaviors.setAttribute(attribute, value);
    }

    public boolean waitUntilVisible() {
        return behaviors.isDisplayed();
    }

    public String getDescription() {
        return description;
    }

    public UiElement setActiveBehavior(String attribute, String value) {
        behaviors.setActiveBehavior(attribute, value);
        return this;
    }

    public UiElement setSelectedBehavior(String attribute, String value) {
        behaviors.setSelectedBehavior(attribute, value);
        return this;
    }

    public void switchTo() {
        behaviors.switchTo();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /***
     * @deprecated Replace with use of UiLocatorType
     */
    @Deprecated
    static class LocatorType {
        public static final String CLASS = "class";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TAG = "tag";
    }
}
