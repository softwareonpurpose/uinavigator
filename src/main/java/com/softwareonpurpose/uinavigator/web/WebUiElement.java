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
import com.softwareonpurpose.uinavigator.ElementBehaviors;
import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiGetElement;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebUiElement implements UiElement {
    private final String description;
    private final ElementBehaviors behaviors;

    private WebUiElement(String description, ElementBehaviors behaviors) {
        this.description = description;
        this.behaviors = behaviors;
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocator(description, locatorType, locatorValue);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttribute(
                        description, locatorType, locatorValue, attribute, attributeValue);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           Integer ordinal) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorOrdinal(description, locatorType, locatorValue, ordinal);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           WebUiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorParent(description, locatorType, locatorValue, parent.getBehavior());
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue,
                                           Integer ordinal) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeOrdinal(description,
                        locatorType, locatorValue, attribute, attributeValue, ordinal);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue,
                                           WebUiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeParent(description,
                        locatorType, locatorValue, attribute, attributeValue, (WebUiGetElement) parent.getBehavior());
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           Integer ordinal,
                                           WebUiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorOrdinalParent(
                        description, locatorType, locatorValue, ordinal, (WebUiGetElement) parent.getBehavior());
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue,
                                           Integer ordinal,
                                           WebUiElement parent) {
        ElementBehaviors behaviors =
                ElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(description,
                        locatorType, locatorValue, attribute, attributeValue, ordinal, (WebUiGetElement) parent.getBehavior());
        return new WebUiElement(description, behaviors);
    }

    public static void suppressLogging(boolean suppressLogging) {
        ElementBehaviors.suppressLogging(suppressLogging);
    }

    public static List<WebUiElement> getList(String description, String locatorType, String locatorValue, WebUiElement parent) {
        List<WebUiElement> elements = new ArrayList<>();
        WebElement parentElement = (WebElement) (parent.getBehavior()).execute();
        List<WebElement> webElements = parentElement != null ? parentElement
                .findElements(WebElementLocator.getInstance(locatorType, locatorValue)) : new ArrayList<>();
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal++) {
            String elementDescription = String.format("%s #%d", description, elementOrdinal);
            elements.add(WebUiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal, parent));
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

    public boolean waitUntilVisible() {
        return behaviors.isDisplayed();
    }

    public void setAttribute(String attribute, String value) {
        behaviors.setAttribute(attribute, value);
    }

    public String getDescription() {
        return description;
    }

    public String getAttribute(String attribute) {
        return behaviors.getAttribute(attribute);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public WebUiElement setActiveBehavior(String attribute, String value) {
        behaviors.setActiveBehavior(attribute, value);
        return this;
    }

    public WebUiElement setSelectedBehavior(String attribute, String value) {
        behaviors.setSelectedBehavior(attribute, value);
        return this;
    }

    public void switchTo() {
        behaviors.switchTo();
    }
}
