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
package com.softwareonpurpose.uinavigator;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of any element of a UI (e.g. web page, Windows or Mac application, etc.)
 */
public class UiElement {
    private transient static String message_unableToFind = "WARNING: Unable to locate element %s";
    private transient static boolean suppressLogging;
    private final String description;
    private final String locatorType;
    private final String locatorValue;
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final UiElement parent;
    private transient String activeClass;
    private transient String selectedClass;
    private transient String selectedStyle;
    private transient By locator;
    private transient GetElementBehavior getElementBehavior;
    private transient ElementBehavior elementBehavior;
    private transient WebElement element;
    private transient IsClickableBehavior isClickableBehavior;

    private UiElement(String description, String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiElement parent) {
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
     * Get an UiElement instance
     *
     * @param description  String description of the element (used for logging)
     * @param locatorType  String (UiElement.LocatorType) defining locator type
     * @param locatorValue String value of locator
     * @return UiElement instantiated
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue) {
        return new UiElement(description, locatorType, locatorValue, null, null, null, null);
    }

    /**
     * Get an UiElement instance
     *
     * @param description  String description of the element (used for logging)
     * @param locatorType  String (UiElement.LocatorType) defining locator type
     * @param locatorValue String value of locator
     * @param parent       UiElement containing requested element
     * @return UiElement instantiated
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue, UiElement parent) {
        return new UiElement(description, locatorType, locatorValue, null, null, null, parent);
    }

    /**
     * Get an UiElement instance
     *
     * @param description  String description of the element (used for logging)
     * @param locatorType  String (UiElement.LocatorType) defining locator type
     * @param locatorValue String value of locator
     * @param ordinal      Int specifying Nth element within parent
     * @param parent       UiElement containing requested element
     * @return UiElement instantiated
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue, int ordinal, UiElement parent) {
        return new UiElement(description, locatorType, locatorValue, null, null, ordinal, parent);
    }

    /**
     * Get an UiElement instance
     *
     * @param description    String description of the element (used for logging)
     * @param locatorType    String (UiElement.LocatorType) defining locator type
     * @param locatorValue   String value of locator
     * @param attribute      String attribute of requested element
     * @param attributeValue String value of attribute
     * @param parent         UiElement containing requested element
     * @return UiElement instantiated
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue, String attribute, String attributeValue, UiElement parent) {
        return new UiElement(description, locatorType, locatorValue, attribute, attributeValue, null, parent);
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
     * @param locatorType  String (UiElement.LocatorType) defining locator type
     * @param locatorValue String value of locator
     * @param parent       UiElement containing requested elements
     * @return List of UiElements
     */
    @SuppressWarnings("unused")
    public static List<UiElement> getList(String description, String locatorType, String locatorValue, UiElement parent) {
        List<UiElement> elements = new ArrayList<>();
        WebElement parentElement = parent.getElement();
        List<WebElement> webElements = parentElement != null ? parentElement
                .findElements(constructLocator(locatorType, locatorValue)) : new ArrayList<>();
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal++) {
            String elementDescription = String.format("%s #%d", description, elementOrdinal);
            elements.add(UiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal, parent));
        }
        return elements;
    }

    private static By constructLocator(String locatorType, String locatorValue) {
        switch (locatorType) {
            case LocatorType.CLASS:
                return By.className(locatorValue);
            case LocatorType.ID:
                return By.id(locatorValue);
            case LocatorType.NAME:
                return By.name(locatorValue);
            case LocatorType.TAG:
                return By.tagName(locatorValue);
            default:
                return null;
        }
    }

    private void initializeLocator() {
        locator = constructLocator(locatorType, locatorValue);
        switch (locatorType) {
            case LocatorType.CLASS:
                isClickableBehavior = new ClassLocatedIsClickableBehavior(locatorValue);
                break;
            case LocatorType.ID:
                isClickableBehavior = new IdLocatedIsClickableBehavior(locatorValue);
                break;
            case LocatorType.NAME:
                isClickableBehavior = new NameLocatedIsClickableBehavior(locatorValue);
                break;
            case LocatorType.TAG:
                isClickableBehavior = new TagLocatedIsClickableBehavior(locatorValue);
        }
    }

    /**
     * Text content of UiElement
     *
     * @return String content
     */
    @SuppressWarnings("WeakerAccess")
    public String getText() {
        return getElementBehavior().getText();
    }

    private ElementBehavior getElementBehavior() {
        if (elementBehavior == null)
            if (getElement() != null && getElement().getTagName() != null && getElement().getTagName().equals("select"))
                elementBehavior = new SelectElementBehavior();
            else elementBehavior = new DefaultElementBehavior();
        return elementBehavior;
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
                getElementBehavior().set(value);
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
        return isClickableBehavior.execute();
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
     * @return UiElement instance
     */
    @SuppressWarnings("unused")
    public UiElement setSelectedClass(String elementClass) {
        this.selectedClass = elementClass;
        return this;
    }

    /**
     * Set 'style' value indicating the UI element is selected
     *
     * @param elementStyle String style value indicating the element is selected
     * @return UiElement instance
     */
    @SuppressWarnings("unused")
    public UiElement setSelectedStyle(String elementStyle) {
        this.selectedStyle = elementStyle;
        return this;
    }

    /**
     * Set 'class' value indicating the UI element is active
     *
     * @param elementClass String class value indicating the element is active
     * @return UiElement instance
     */
    @SuppressWarnings("unused")
    public UiElement setActiveClass(String elementClass) {
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
        return UiHost.getInstance().waitUntilVisible(locatorType, locatorValue);
    }

    /**
     * Src value
     *
     * @return String value of 'src' attribute
     */
    @SuppressWarnings("unused")
    public String getSrc() {
        final WebElement element = getElement();
        return element == null ? null : element.getAttribute(Attribute.SRC);
    }

    /**
     * Set the attribute of a UI element to a given value
     *
     * @param attribute String attribute name
     * @param value     String attribute value
     */
    @SuppressWarnings("WeakerAccess")
    public void setAttribute(String attribute, String value) {
        UiHost.getInstance().setAttribute(locatorType, locatorValue, attribute, value);
    }

    /**
     * Description
     *
     * @return String description
     */
    String getDescription() {
        return description;
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger("");
    }

    private WebElement getElement() {
        if (parent == null) UiHost.getInstance().selectWindow();
        if (element == null) element = getElementBehavior.execute();
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
        return element == null ? null : element.getAttribute(Attribute.STYLE);
    }

    private String getClassName() {
        return getAttribute("class");
    }

    private void initializeGetElementBehavior() {
        if (parent == null && attribute == null && ordinal == null) getElementBehavior = new GetView();
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

    @SuppressWarnings("WeakerAccess")
    public class LocatorType {
        public static final String CLASS = "class";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TAG = "tag";
    }
    
    @SuppressWarnings("WeakerAccess")
    public class Attribute {
        public static final String STYLE = "style";
        public static final String CLASS = "class";
        private static final String HREF = "href";
        private static final String SRC = "src";
    }

    /**
     * ** IS CLICKABLE BEHAVIORS ****
     */
    private interface IsClickableBehavior {
        boolean execute();
    }

    private class ClassLocatedIsClickableBehavior implements IsClickableBehavior {
        private final String locatorValue;

        private ClassLocatedIsClickableBehavior(String locatorValue) {
            this.locatorValue = locatorValue;
        }

        @Override
        public boolean execute() {
            return getElement().getAttribute("class").contains(locatorValue);
        }
    }

    private class IdLocatedIsClickableBehavior implements IsClickableBehavior {
        private final String locatorValue;

        private IdLocatedIsClickableBehavior(String locatorValue) {
            this.locatorValue = locatorValue;
        }

        @Override
        public boolean execute() {
            return getElement().getAttribute("id").contains(locatorValue);
        }
    }

    private class NameLocatedIsClickableBehavior implements IsClickableBehavior {
        private final String locatorValue;

        private NameLocatedIsClickableBehavior(String locatorValue) {
            this.locatorValue = locatorValue;
        }

        @Override
        public boolean execute() {
            return getElement().getAttribute("name").contains(locatorValue);
        }
    }

    private class TagLocatedIsClickableBehavior implements IsClickableBehavior {
        private final String locatorValue;

        private TagLocatedIsClickableBehavior(String locatorValue) {
            this.locatorValue = locatorValue;
        }

        @Override
        public boolean execute() {
            return getElement().getTagName().contains(locatorValue);
        }
    }

    /**
     * ** GET ELEMENT BEHAVIORS ****
     */

    private interface GetElementBehavior {
        WebElement execute();
    }

    protected class GetView implements GetElementBehavior {

        public WebElement execute() {
            UiHost host = UiHost.getInstance();
            return host.findUiElement(locatorType, locatorValue);
        }
    }

    protected class GetView_attribute implements GetElementBehavior {

        public WebElement execute() {
            UiHost host = UiHost.getInstance();
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

    /**
     * ** ELEMENT BEHAVIORS ****
     */
    private interface ElementBehavior {
        void set(String text);

        String getText();
    }

    private class SelectElementBehavior implements ElementBehavior {

        @Override
        public void set(String text) {
            new Select(getElement()).selectByVisibleText(text);
        }

        @Override
        public String getText() {
            Select select = new Select(getElement());
            return select.getOptions().size() == 0 ? null : select.getFirstSelectedOption().getText();
        }
    }

    private class DefaultElementBehavior implements ElementBehavior {
        @Override
        public void set(String text) {
            getElement().clear();
            getElement().sendKeys(text);
        }

        @Override
        public String getText() {
            final WebElement element = getElement();
            return element == null ? null : element.getTagName().equals("input") ? element.getAttribute("value") : element.getText();
        }
    }
}
