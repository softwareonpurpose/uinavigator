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
import com.softwareonpurpose.uinavigator.web.WebElementGet;
import com.softwareonpurpose.uinavigator.web.WebElementLocator;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UiElement {
    private final String description;
    private final UiElementBehaviors behaviors;

    private UiElement(String description, UiElementBehaviors behaviors) {
        this.description = description;
        this.behaviors = behaviors;
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocator(description, locatorType, locatorValue, host);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttribute(
                        description, locatorType, locatorValue, attribute, attributeValue, host);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        Integer ordinal, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorOrdinal(description, locatorType, locatorValue, ordinal, host);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        UiElement parent, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorParent(description, locatorType, locatorValue, parent.getBehavior(), host);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue,
                                        Integer ordinal, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinal(description,
                        locatorType, locatorValue, attribute, attributeValue, ordinal, host);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue,
                                        UiElement parent, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeParent(description,
                        locatorType, locatorValue, attribute, attributeValue, (WebElementGet) parent.getBehavior(), host);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        Integer ordinal,
                                        UiElement parent, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorOrdinalParent(
                        description, locatorType, locatorValue, ordinal, (WebElementGet) parent.getBehavior(), host);
        return new UiElement(description, behaviors);
    }

    public static UiElement getInstance(String description, String locatorType, String locatorValue,
                                        String attribute, String attributeValue,
                                        Integer ordinal,
                                        UiElement parent, UiHost host) {
        UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(description,
                        locatorType, locatorValue, attribute, attributeValue, ordinal, (WebElementGet) parent.getBehavior(), host);
        return new UiElement(description, behaviors);
    }

    public static void suppressLogging(boolean suppressLogging) {
        UiElementBehaviors.suppressLogging(suppressLogging);
    }

    public static List<UiElement> getList(String description, String locatorType, String locatorValue, UiElement parent, UiHost host) {
        List<UiElement> elements = new ArrayList<>();
        WebElement parentElement = (WebElement) (parent.getBehavior()).execute();
        List<WebElement> webElements = parentElement != null ? parentElement
                .findElements(WebElementLocator.getInstance(locatorType, locatorValue)) : new ArrayList<>();
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal++) {
            String elementDescription = String.format("%s #%d", description, elementOrdinal);
            elements.add(UiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal, parent, host));
        }
        return elements;
    }

    public static boolean isLoggingSuppressed() {
        return UiElementBehaviors.isLoggingSuppressed();
    }

    private UiElementGet getBehavior() {
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public UiElementGet getLocator() {
        return getBehavior();
    }
}
