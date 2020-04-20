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
import com.softwareonpurpose.uinavigator.UiAttribute;
import com.softwareonpurpose.uinavigator.UiElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WebUiElement implements UiElement {
    private static final WebGetElementByLocator defaultParentLocator =
            WebGetElementByLocator.getInstance(new By.ByTagName("body"));
    private transient static boolean suppressLogging;
    private final String description;
    private transient String activeClass;
    private transient String selectedClass;
    private transient String selectedStyle;
    private WebUiElementBehaviors behaviors;

    private WebUiElement(String description, WebUiElementBehaviors behaviors) {
        this.description = description;
        this.behaviors = behaviors;
    }

    public static WebUiElement getInstance(String description, By locator) {
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocator(locator);
        return new WebUiElement(description, behaviors);
    }


    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttribute(locator, attribute, attributeValue);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           Integer ordinal) {
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocatorOrdinal(locator, ordinal);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors = WebUiElementBehaviors.getInstanceByLocatorParent(locator, getParent);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue,
                                           Integer ordinal) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinal(
                        locator, attribute, attributeValue, ordinal);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue,
                                           WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeParent(
                        locator, attribute, attributeValue, getParent);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           Integer ordinal,
                                           WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorOrdinalParent(locator, ordinal, getParent);
        return new WebUiElement(description, behaviors);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue,
                                           Integer ordinal,
                                           WebGetElementBehavior getParent) {
        WebUiElementBehaviors behaviors =
                WebUiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent(
                        locator, attribute, attributeValue, ordinal, getParent);
        return new WebUiElement(description, behaviors);
    }

    public static void suppressLogging(boolean suppress) {
        suppressLogging = suppress;
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

    public String getText() {
        return behaviors.getText();
    }

    public String getHref() {
        return getAttribute("href");
    }

    public void set(String value) {
        value = value == null ? "" : value;
        WebElement element = getElement();
        if (!suppressLogging) {
            getLogger().info(String.format(getIndentation() + "Set %s to \"%s\"", getDescription(), value));
        }
        String message_unableToSet = "BLOCKED: Unable to set %s to \"%s\" using element hierarchy %s";
        if (element != null) {
            try {
                behaviors.set(value);
            } catch (WebDriverException e) {
                final String errorMessage = String.format(message_unableToSet, getDescription(), value, this.toString());
                reportException(e, errorMessage);
            }
        } else {
            if (!suppressLogging) {
                getLogger().error(String.format(message_unableToSet, description, value, this.toString()));
            }
        }
    }

    public void click() {
        if (!suppressLogging) {
            getLogger().info(String.format(getIndentation() + "Click %s", getDescription()));
        }
        WebElement element = getElement();
        final String errorMessage = String.format("BLOCKED: Unable to click %s using hierarchy %s", getDescription(), this.toString());
        if (element != null && !"".equals(element.getTagName())) {
            try {
                element.click();
            } catch (WebDriverException e) {
                reportException(e, errorMessage);
            }
        } else {
            if (!suppressLogging) {
                getLogger().error(errorMessage);
            }
        }
        waitForPotentialStateTransitionToBegin();
    }

    private void waitForPotentialStateTransitionToBegin() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return classContains(activeClass);
    }

    public boolean isSelected() {
        WebElement element = getElement();
        return classContains(selectedClass) || styleContains(selectedStyle) || (element != null && element.isSelected());
    }

    public boolean isDisplayed() {
        return this.waitUntilVisible();
    }

    public WebUiElement setSelectedClass(String elementClass) {
        this.selectedClass = elementClass;
        return this;
    }

    public WebUiElement setSelectedStyle(String elementStyle) {
        this.selectedStyle = elementStyle;
        return this;
    }

    public WebUiElement setActiveClass(String elementClass) {
        this.activeClass = elementClass;
        return this;
    }

    public boolean waitUntilVisible() {
        final WebElement element = this.getElement();
        return element != null && WebUiHost.getInstance().waitUntilVisible(element);
    }

    public String getSrc() {
        final WebElement element = getElement();
        return element == null ? null : element.getAttribute(UiAttribute.SRC);
    }

    public void setAttribute(String attribute, String value) {
        WebUiHost.getInstance().setAttribute(getElement(), attribute, value);
    }

    public String getDescription() {
        return description;
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger("");
    }

    private WebElement getElement() {
        return (WebElement) behaviors.get();
    }

    private String getIndentation() {
        return new String(new char[4]).replace('\0', ' ');
    }

    private boolean classContains(String value) {
        String className = getClassName();
        if (className == null || value == null) return false;
        return className.contains(value);
    }

    private boolean styleContains(String value) {
        String style = getStyle();
        if (style == null || value == null) return false;
        return style.contains(value);
    }

    private String getStyle() {
        WebElement element = getElement();
        return element == null ? null : element.getAttribute(UiAttribute.STYLE);
    }

    private String getClassName() {
        return getAttribute("class");
    }

    private void reportException(WebDriverException e, String errorMessage) {
        getLogger().error(errorMessage);
        e.printStackTrace();
        throw new WebDriverException(errorMessage);
    }

    public String getAttribute(String attribute) {
        return behaviors.getAttribute(attribute);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
