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

/**
 * Representation of any element of a UI (e.g. web page, Windows or Mac application, etc.)
 */
public class WebUiElement implements UiElement {
    private static final WebGetElementByLocator defaultParentLocator =
            WebGetElementByLocator.getInstance(new By.ByTagName("body"));
    private transient static boolean suppressLogging;
    private final String description;
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;
    private transient String activeClass;
    private transient String selectedClass;
    private transient String selectedStyle;
    private WebUiElementBehaviors elementBehaviors;

    private WebUiElement(String description, By locator,
                         String attribute, String attributeValue,
                         Integer ordinal,
                         WebGetElementBehavior getParent) {
        elementBehaviors = WebUiElementBehaviors.getInstance(
                locator,
                attribute, attributeValue,
                ordinal,
                getParent);
        this.description = description;
        boolean isBodyTag = new By.ByTagName("body").equals(locator);
        this.getParent = getParent != null ? getParent : isBodyTag ? null : defaultParentLocator;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
    }

    public static WebUiElement getInstance(String description, By locator) {
        return new WebUiElement(description, locator,
                null, null, null, null);
    }


    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue) {
        return new WebUiElement(description, locator,
                attribute, attributeValue, null, null);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           int ordinal) {
        return new WebUiElement(description, locator,
                null, null, ordinal, null);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           WebGetElementByLocator getParent) {
        return new WebUiElement(description, locator,
                null, null, null, getParent);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue,
                                           int ordinal) {
        return new WebUiElement(description, locator, attribute, attributeValue, ordinal, null);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue,
                                           WebGetElementByLocator getParent) {
        return new WebUiElement(description, locator, attribute, attributeValue, null, getParent);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           int ordinal,
                                           WebGetElementByLocator getParent) {
        return new WebUiElement(description, locator, null, null, ordinal, getParent);
    }

    public static WebUiElement getInstance(String description, By locator,
                                           String attribute, String attributeValue,
                                           int ordinal,
                                           WebGetElementByLocator getParent) {
        return new WebUiElement(description, locator, attribute, attributeValue, ordinal, getParent);
    }

    /**
     * Suppresses logging of interactions all UiElements
     *
     * @param suppress boolean logging preference
     */
    @SuppressWarnings("unused")
    public static void suppressLogging(boolean suppress) {
        suppressLogging = suppress;
    }

    @SuppressWarnings("unused")
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

    /**
     * Text content of WebUiElement
     *
     * @return String content
     */
    public String getText() {
        return elementBehaviors.getText();
    }

    /**
     * Href value
     *
     * @return String href of the defined UI element
     */
    public String getHref() {
        return getAttribute("href");
    }

    /**
     * Sets value of the UI element (i.e. an 'input' field)
     *
     * @param value String value
     */
    public void set(String value) {
        value = value == null ? "" : value;
        WebElement element = getElement();
        if (!suppressLogging) {
            getLogger().info(String.format(getIndentation() + "Set %s to \"%s\"", getDescription(), value));
        }
        String message_unableToSet = "BLOCKED: Unable to set %s to \"%s\" using element hierarchy %s";
        if (element != null) {
            try {
                elementBehaviors.set(value);
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

    /**
     * Click the UI element
     */
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

    /**
     * 'Active' state
     *
     * @return boolean 'active' state
     */
    @SuppressWarnings("unused")
    public boolean isActive() {
        return classContains(activeClass);
    }

    /**
     * 'Selected' state
     *
     * @return boolean 'selected' state
     */
    @SuppressWarnings("unused")
    public boolean isSelected() {
        WebElement element = getElement();
        return classContains(selectedClass) || styleContains(selectedStyle) || (element != null && element.isSelected());
    }

    /**
     * 'Displayed' state
     *
     * @return boolean 'displayed' state
     */
    @SuppressWarnings("unused")
    public boolean isDisplayed() {
        return this.waitUntilVisible();
    }

    /**
     * Set 'class' value indicating the UI element is selected
     *
     * @param elementClass String class value indicating the element is selected
     * @return WebUiElement instance
     */
    @SuppressWarnings("unused")
    public WebUiElement setSelectedClass(String elementClass) {
        this.selectedClass = elementClass;
        return this;
    }

    /**
     * Set 'style' value indicating the UI element is selected
     *
     * @param elementStyle String style value indicating the element is selected
     * @return WebUiElement instance
     */
    @SuppressWarnings("unused")
    public WebUiElement setSelectedStyle(String elementStyle) {
        this.selectedStyle = elementStyle;
        return this;
    }

    /**
     * Set 'class' value indicating the UI element is active
     *
     * @param elementClass String class value indicating the element is active
     * @return WebUiElement instance
     */
    @SuppressWarnings("unused")
    public WebUiElement setActiveClass(String elementClass) {
        this.activeClass = elementClass;
        return this;
    }

    /**
     * 'Visible' state
     *
     * @return boolean indicating whether the element was visible within the defined timeout period
     */
    public boolean waitUntilVisible() {
        final WebElement element = this.getElement();
        return element != null && WebUiHost.getInstance().waitUntilVisible(element);
    }

    /**
     * Src value
     *
     * @return String value of 'src' attribute
     */
    public String getSrc() {
        final WebElement element = getElement();
        return element == null ? null : element.getAttribute(UiAttribute.SRC);
    }

    /**
     * Set the attribute of a UI element to a given value
     *
     * @param attribute String attribute name
     * @param value     String attribute value
     */
    public void setAttribute(String attribute, String value) {
        WebUiHost.getInstance().setAttribute(getElement(), attribute, value);
    }

    /**
     * Description
     *
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger("");
    }

    private WebElement getElement() {
        return (WebElement) elementBehaviors.get();
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

    /**
     * Value of an attribute
     *
     * @param attribute String attribute name
     * @return String attribute value
     */
    @SuppressWarnings("WeakerAccess")
    public String getAttribute(String attribute) {
        WebElement element = getElement();
        return element == null ? null : attribute == null ? null : element.getAttribute(attribute);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
