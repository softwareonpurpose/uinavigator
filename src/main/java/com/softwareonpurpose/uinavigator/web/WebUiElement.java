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
import com.softwareonpurpose.uinavigator.UiElementBehaviors;
import com.softwareonpurpose.uinavigator.UiLocatorType;
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
    private transient static String message_unableToFind = "WARNING: Unable to locate element %s";
    private transient static boolean suppressLogging;
    private final String description;
    private final String locatorType;
    private final String locatorValue;
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final WebUiElement parent;
    private transient String activeClass;
    private transient String selectedClass;
    private transient String selectedStyle;
    private transient By locator;
    private transient GetElementBehavior getElementBehavior;
    private transient WebElement element;
    private UiElementBehaviors elementBehaviors;

    private WebUiElement(String description, String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, WebUiElement parent) {
        elementBehaviors = UiElementBehaviors.getInstance(locatorType, locatorValue);
        this.description = description;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        initializeLocator();
        this.parent = parent;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
        initializeGetElementBehavior();
    }

    /**
     * Get an WebUiElement instance
     *
     * @param description  String description of the element (used for logging)
     * @param locatorType  String (WebUiElement.UiLocatorType) defining locator type
     * @param locatorValue String value of locator
     * @return WebUiElement instantiated
     */
    public static WebUiElement getInstance(String description, String locatorType, String locatorValue) {
        return new WebUiElement(description, locatorType, locatorValue, null, null, null, null);
    }

    /**
     * Get an WebUiElement instance
     *
     * @param description  String description of the element (used for logging)
     * @param locatorType  String (WebUiElement.UiLocatorType) defining locator type
     * @param locatorValue String value of locator
     * @param parent       WebUiElement containing requested element
     * @return WebUiElement instantiated
     */
    public static WebUiElement getInstance(String description, String locatorType, String locatorValue, WebUiElement parent) {
        return new WebUiElement(description, locatorType, locatorValue, null, null, null, parent);
    }

    /**
     * Get an WebUiElement instance
     *
     * @param description  String description of the element (used for logging)
     * @param locatorType  String (WebUiElement.UiLocatorType) defining locator type
     * @param locatorValue String value of locator
     * @param ordinal      Int specifying Nth element within parent
     * @param parent       WebUiElement containing requested element
     * @return WebUiElement instantiated
     */
    public static WebUiElement getInstance(String description, String locatorType, String locatorValue, int ordinal, WebUiElement parent) {
        return new WebUiElement(description, locatorType, locatorValue, null, null, ordinal, parent);
    }

    /**
     * Get an WebUiElement instance
     *
     * @param description    String description of the element (used for logging)
     * @param locatorType    String (WebUiElement.UiLocatorType) defining locator type
     * @param locatorValue   String value of locator
     * @param attribute      String attribute of requested element
     * @param attributeValue String value of attribute
     * @param parent         WebUiElement containing requested element
     * @return WebUiElement instantiated
     */
    public static WebUiElement getInstance(String description, String locatorType, String locatorValue, String attribute, String attributeValue, WebUiElement parent) {
        return new WebUiElement(description, locatorType, locatorValue, attribute, attributeValue, null, parent);
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

    /**
     * List of UiElements matching parameters
     *
     * @param description  String description of the elements (used for logging)
     * @param locatorType  String (WebUiElement.UiLocatorType) defining locator type
     * @param locatorValue String value of locator
     * @param parent       WebUiElement containing requested elements
     * @return List of UiElements
     */
    @SuppressWarnings("unused")
    public static List<WebUiElement> getList(String description, String locatorType, String locatorValue, WebUiElement parent) {
        List<WebUiElement> elements = new ArrayList<>();
        WebElement parentElement = parent.getElement();
        List<WebElement> webElements = parentElement != null ? parentElement
                .findElements(constructLocator(locatorType, locatorValue)) : new ArrayList<>();
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal++) {
            String elementDescription = String.format("%s #%d", description, elementOrdinal);
            elements.add(WebUiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal, parent));
        }
        return elements;
    }

    private static By constructLocator(String locatorType, String locatorValue) {
        switch (locatorType) {
            case UiLocatorType.CLASS:
                return By.className(locatorValue);
            case UiLocatorType.ID:
                return By.id(locatorValue);
            case UiLocatorType.NAME:
                return By.name(locatorValue);
            case UiLocatorType.TAG:
                return By.tagName(locatorValue);
            default:
                return null;
        }
    }

    private void initializeLocator() {
        locator = constructLocator(locatorType, locatorValue);
        switch (locatorType) {
            case UiLocatorType.CLASS:
                break;
            case UiLocatorType.ID:
                break;
            case UiLocatorType.NAME:
                break;
            case UiLocatorType.TAG:
        }
    }

    /**
     * Text content of WebUiElement
     *
     * @return String content
     */
    @SuppressWarnings("WeakerAccess")
    public String getText() {
        return elementBehaviors.getText();
    }

    /**
     * Href value
     *
     * @return String href of the defined UI element
     */
    @SuppressWarnings("WeakerAccess")
    public String getHref() {
        return getAttribute("href");
    }

    /**
     * Sets value of the UI element (i.e. an 'input' field)
     *
     * @param value String value
     */
    @SuppressWarnings("WeakerAccess")
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
                getLogger().error(String.format(message_unableToSet, locator.toString(), value, this.toString()));
            }
        }
    }

    /**
     * Click the UI element
     */
    @SuppressWarnings("WeakerAccess")
    public void click() {
        if (!suppressLogging) {
            getLogger().info(String.format(getIndentation() + "Click %s", getDescription()));
        }
        WebElement element = getElement();
        final String errorMessage = String.format("BLOCKED: Unable to click %s using hierarchy %s", getDescription(), this.toString());
        if (element != null && isClickable()) {
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

    private boolean isClickable() {
        return getElement().getAttribute(locatorType).contains(locatorValue);
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
    @SuppressWarnings("WeakerAccess")
    public boolean waitUntilVisible() {
        return WebUiHost.getInstance().waitUntilVisible(locatorType, locatorValue);
    }

    /**
     * Src value
     *
     * @return String value of 'src' attribute
     */
    @SuppressWarnings("unused")
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
    @SuppressWarnings("WeakerAccess")
    public void setAttribute(String attribute, String value) {
        WebUiHost.getInstance().setAttribute(locatorType, locatorValue, attribute, value);
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

    WebElement getElement() {
        if (parent == null) WebUiHost.getInstance().selectWindow();
        if (element == null) element = (WebElement) elementBehaviors.get();
        return element;
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

    private void initializeGetElementBehavior() {
        if (parent == null && attribute == null && ordinal == null)
            elementBehaviors = UiElementBehaviors.getInstance(locatorType, locatorValue);
        else if (parent == null) getElementBehavior = new GetView_attribute();
        else if (attribute != null) getElementBehavior = new GetElement_attribute();
        else getElementBehavior = new GetElement();
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

    /**
     * ** ELEMENT BEHAVIORS ****
     */
    private interface ElementBehavior {
        void set(String text);

        String getText();
    }

    /**
     * ** GET ELEMENT BEHAVIORS ****
     */

    private interface GetElementBehavior {
        WebElement execute();
    }

    protected class GetView_attribute implements GetElementBehavior {

        public WebElement execute() {
            WebUiHost host = WebUiHost.getInstance();
            for (WebElement candidate : host.findUiElements(locatorType, locatorValue)) {
                final String candidateAttributeValue = candidate.getAttribute(attribute);
                if (candidateAttributeValue != null && candidateAttributeValue.contains(attributeValue))
                    return candidate;
            }
            return null;
        }
    }

    protected class GetElement implements GetElementBehavior {

        public WebElement execute() {
            List<WebElement> elements;
            int elementIndex = (ordinal == null || ordinal == 0) ? 0 : ordinal - 1;
            try {
                final WebElement parentElement = parent.getElement();
                elements = parentElement == null ? new ArrayList<>() : parentElement.findElements(locator);
            } catch (WebDriverException | NullPointerException e) {
                getLogger().warn(String.format(message_unableToFind, locator.toString()));
                return null;
            }
            if (elements.size() > elementIndex) {
                return elements.get(elementIndex);
            } else {
                getLogger().warn(String.format(message_unableToFind, locator.toString()));
            }
            return null;
        }
    }

    protected class GetElement_attribute implements GetElementBehavior {

        public WebElement execute() {
            List<WebElement> elements = new ArrayList<>();
            int elementIndex = (ordinal == null || ordinal == 0) ? 0 : ordinal - 1;
            try {
                final WebElement parentElement = parent.getElement();
                List<WebElement> candidates = parentElement == null ? new ArrayList<>() : parentElement
                        .findElements(locator);
                for (WebElement candidate : candidates) {
                    final String candidateAttributeValue = candidate.getAttribute(attribute);
                    if (candidateAttributeValue != null && candidateAttributeValue.contains(attributeValue))
                        elements.add(candidate);
                }
            } catch (WebDriverException | NullPointerException e) {
                getLogger().warn(String.format(message_unableToFind, locator.toString()));
                return null;
            }
            if (elements.size() > elementIndex) {
                return elements.get(elementIndex);
            } else {
                getLogger().warn(String.format(message_unableToFind, locator.toString()));
            }
            return null;
        }
    }
}
