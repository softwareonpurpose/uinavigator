/*
  Copyright 2019 Craig A. Stockton
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
package com.softwareonpurpose.uinavigator.web;

import com.google.gson.Gson;
import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;

import java.util.ArrayList;
import java.util.List;

public class WebUiElement implements UiElement {
    private final String description;
    private final WebUiElementBehaviors behaviors;

    private WebUiElement(String description, WebUiElementBehaviors behaviors) {
        this.description = description;
        this.behaviors = behaviors;
    }

    static WebUiElement getInstance(String description, By locator) {
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocator(description, locator);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue) {
        return getInstance(description, getLocator(locatorType, locatorValue));
    }

    static WebUiElement getInstance(String description, By locator,
                                    String attribute, String attributeValue) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttribute(description, locator, attribute, attributeValue);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue) {
        return getInstance(description, getLocator(locatorType, locatorValue), attribute, attributeValue);
    }

    static WebUiElement getInstance(String description, By locator,
                                    Integer ordinal) {
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocatorOrdinal(description, locator, ordinal);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           Integer ordinal) {
        return getInstance(description, getLocator(locatorType, locatorValue), ordinal);
    }

    static WebUiElement getInstance(String description, By locator,
                                    WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocatorParent(description, locator, getParent);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           WebGetElementBehavior getParent) {
        return getInstance(description, getLocator(locatorType, locatorValue), getParent);
    }

    static WebUiElement getInstance(String description, By locator,
                                    String attribute, String attributeValue,
                                    Integer ordinal) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinal(description,
                        locator, attribute, attributeValue, ordinal);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue,
                                           Integer ordinal) {
        return getInstance(description, getLocator(locatorType, locatorValue), attribute, attributeValue, ordinal);
    }

    static WebUiElement getInstance(String description, By locator,
                                    String attribute, String attributeValue,
                                    WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeParent(description,
                        locator, attribute, attributeValue, getParent);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue,
                                           WebGetElementBehavior getParent) {
        return getInstance(description, getLocator(locatorType, locatorValue), attribute, attributeValue, getParent);
    }

    static WebUiElement getInstance(String description, By locator,
                                    Integer ordinal,
                                    WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorOrdinalParent(description, locator, ordinal, getParent);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           Integer ordinal,
                                           WebGetElementBehavior getParent) {
        return getInstance(description, getLocator(locatorType, locatorValue), ordinal, getParent);
    }


    static WebUiElement getInstance(String description, By locator,
                                    String attribute, String attributeValue,
                                    Integer ordinal,
                                    WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(description,
                        locator, attribute, attributeValue, ordinal, getParent);
        return new WebUiElement(description, behaviors);
    }


    public static WebUiElement getInstance(String description, String locatorType, String locatorValue,
                                           String attribute, String attributeValue,
                                           Integer ordinal,
                                           WebGetElementBehavior getParent) {
        final By locator = getLocator(locatorType, locatorValue);
        return getInstance(description, locator, attribute, attributeValue, ordinal, getParent);
    }

    private static By getLocator(String locatorType, String locatorValue) {
        By locator;
        switch (locatorType) {
            case UiLocatorType.CLASS:
                locator = new By.ByClassName(locatorValue);
                break;
            case UiLocatorType.TAG:
                locator = new By.ByTagName(locatorValue);
                break;
            case UiLocatorType.ID:
            case UiLocatorType.NAME:
            default:
                locator = new ByIdOrName(locatorValue);
        }
        return locator;
    }

    public static void suppressLogging(boolean suppressLogging) {
        WebUiElementBehaviors.suppressLogging(suppressLogging);
    }

    public static List<WebUiElement> getList(String description, By locator, WebGetElementByLocator getParent) {
        List<WebUiElement> elements = new ArrayList<>();
        WebElement parentElement = getParent.execute();
        List<WebElement> webElements = parentElement != null ? parentElement
                .findElements(locator) : new ArrayList<>();
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal++) {
            String elementDescription = String.format("%s #%d", description, elementOrdinal);
            elements.add(WebUiElement.getInstance(elementDescription, locator, elementOrdinal, getParent));
        }
        return elements;
    }

    public static boolean isLoggingSuppressed() {
        return WebUiElementBehaviors.isLoggingSuppressed();
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

    public String getSrc() {
        return behaviors.getSrc();
    }

    public void setAttribute(String attribute, String value) {
        behaviors.setAttribute(attribute, value);
    }

    String getDescription() {
        return description;
    }

    public String getAttribute(String attribute) {
        return behaviors.getAttribute(attribute);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public WebUiElement setActiveBehavior(String attribute, String value) {
        behaviors.setActiveBehavior(attribute, value);
        return this;
    }

    @Override
    public Object setSelectedBehavior(String attribute, String value) {
        behaviors.setSelectedBehavior(attribute, value);
        return this;
    }
}
