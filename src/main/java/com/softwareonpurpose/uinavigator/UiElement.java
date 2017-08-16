/**
 * Copyright 2017 Craig A. Stockton
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class UiElement {

    private static String message_unableToFind = "WARNING: Unable to locate %s";
    private static boolean suppressLogging;
    private final UiElement parent;
    private final String description;
    private final String attribute;
    private final String attributeValue;
    private final int ordinal;
    private final String frameId;
    private final String locatorType;
    private final String locatorValue;
    private String activeClass;
    private String selectedClass;
    private String selectedStyle;
    private String tipAttribute = "title";
    private By locator;
    private GetElementBehavior getElementBehavior;
    private ElementBehavior elementBehavior;
    private WebElement element;
    private IsClickableBehavior isClickableBehavior;

    private UiElement(String description, String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiElement parent) {
        this.description = description;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        frameId = locatorType.equals(LocatorType.FRAME) ? locatorValue : null;
        initializeLocator();
        this.parent = parent;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal == null ? 1 : ordinal;
        initializeGetElementBehavior();
    }

    /**
     * @param description  String description of the element used for logging
     * @param locatorType  String (UiElement.LocatorType) defining Selenium locator type
     * @param locatorValue String value of locator
     * @return UiElement instantiated with the defined locator and description
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue) {
        return new UiElement(description, locatorType, locatorValue, null, null, null, null);
    }

    /**
     * @param description  String description of the element used for logging
     * @param locatorType  String (UiElement.LocatorType) defining Selenium locator type
     * @param locatorValue String value of locator
     * @param parent       UiElement representing a parent of the requested element
     * @return UiElement instantiated with the defined locator and description
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue, UiElement parent) {
        return new UiElement(description, locatorType, locatorValue, null, null, null, parent);
    }

    /**
     * @param description  String description of the element used for logging
     * @param locatorType  String (UiElement.LocatorType) defining Selenium locator type
     * @param locatorValue String value of locator
     * @param ordinal      Int specifying the Nth element within the UiElement parent
     * @param parent       UiElement representing a parent of the requested element
     * @return UiElement instantiated with the defined locator and description
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue, int ordinal, UiElement parent) {
        return new UiElement(description, locatorType, locatorValue, null, null, ordinal, parent);
    }

    /**
     * @param description    String description of the element used for logging
     * @param locatorType    String (UiElement.LocatorType) defining Selenium locator type
     * @param locatorValue   String value of locator
     * @param attribute      String defining the attribute of the requested WebElement
     * @param attributeValue String defining the value of the attribute
     * @param parent         UiElement representing a parent of the requested element
     * @return UiElement instantiated with the defined locator and description
     */
    public static UiElement getInstance(String description, String locatorType, String locatorValue, String attribute, String attributeValue, UiElement parent) {
        return new UiElement(description, locatorType, locatorValue, attribute, attributeValue, null, parent);
    }

    public static void suppressLogging(boolean suppress) {
        suppressLogging = suppress;
    }

    /**
     * @param description  String description of the elements used for logging
     * @param locatorType  String (UiElement.LocatorType) defining Selenium locator type
     * @param locatorValue String value of locator
     * @param parent       UiElement representing a parent of the requested elements
     * @return List of HtmlElements instantiated with the defined locator and description
     */
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
            case LocatorType.FRAME:
                return By.tagName("body");
        }
        return null;
    }

    private By initializeLocator() {
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
        return locator;
    }

    /**
     * @return The text of the defined WebElement
     */
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
     * @return The href of the defined WebElement
     */
    public String getHref() {
        final WebElement element = getElement();
        return element == null ? null : element.getAttribute(Attribute.HREF);
    }

    /**
     * @return String tooltip of the defined WebElement  (e.g. 'title' attribute)
     */
    public String getTip() {
        final WebElement element = getElement();
        return element == null ? null : tipAttribute == null ? null : element.getAttribute(tipAttribute);
    }

    /**
     * @param attribute String name of an attribute of the element
     * @return String value of the requested attribute
     */
    public UiElement setTipAttribute(String attribute) {
        this.tipAttribute = attribute;
        return this;
    }

    /**
     * Sets the value of the element (e.g. a textbox)
     *
     * @param value String value
     */
    public void set(String value) {
        value = value == null ? "" : value;
        WebElement element = getElement();
        if (!suppressLogging) {
            getLogger().info(String.format(getIndentation() + "Set %s to \"%s\"", getDescription(), value));
        }
        String message_unableToSet = "BLOCKED: Unable to set %s element to \"%s\"";
        if (element != null) {
            try {
                getElementBehavior().set(value);
            } catch (WebDriverException e) {
                final String errorMessage = String.format(message_unableToSet, locator.toString(), value);
                reportException(e, errorMessage);
            }
        } else {
            if (!suppressLogging) {
                getLogger().error(String.format(message_unableToSet, locator.toString(), value));
            }
        }
    }

    /**
     * Executes a mouse-click event
     */
    public void click() {
        if (!suppressLogging) {
            getLogger().info(String.format(getIndentation() + "Click %s", getDescription()));
        }
        WebElement element = getElement();
        final String errorMessage = String.format("BLOCKED: Unable to click %s", getElementDescription());
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
    }

    private boolean isClickable() {
        return isClickableBehavior.execute();
    }

    /**
     * @return boolean indicating whether the element is active
     */
    public boolean isActive() {
        return classContains(activeClass);
    }

    /**
     * @return boolean indicating whether the element is selected
     */
    public boolean isSelected() {
        WebElement element = getElement();
        return classContains(selectedClass) || styleContains(selectedStyle) || (element != null && element.isSelected());
    }

    /**
     * @return boolean indicating whether the element is displayed
     */
    public boolean isDisplayed() {
        return this.waitUntilVisible();
    }

    /**
     * Sets the 'class' value used to indicate that the element is selected
     *
     * @param selectedClassValue String value used to indicate that the element is selected
     * @return This instance of UiElement
     */
    public UiElement setSelectedClass(String selectedClassValue) {
        this.selectedClass = selectedClassValue;
        return this;
    }

    /**
     * Sets the 'style' value used to indicate that the element is selected
     *
     * @param selectedStyleValue String value used to indicate that the element is selected
     * @return This instance of UiElement
     */
    public UiElement setSelectedStyle(String selectedStyleValue) {
        this.selectedStyle = selectedStyleValue;
        return this;
    }

    /**
     * Sets the 'active' value used to indicate that the element is active
     *
     * @param activeClass String value used to indicate that the element is acdtive
     * @return This instance of UiElement
     */
    public UiElement setActiveClass(String activeClass) {
        this.activeClass = activeClass;
        return this;
    }

    /**
     * @return boolean indicating whether the element was visible within the defined timeout period
     */
    public boolean waitUntilVisible() {
        return UiHost.getInstance().waitUntilVisible(locator);
    }

    /**
     * @return String value of the elements 'src' attribute
     */
    public String getSrc() {
        final WebElement element = getElement();
        return element == null ? null : element.getAttribute(Attribute.SRC);
    }

    public void setAttribute(String attributeName, String value) {
        UiHost.getInstance().execute(getElement(), attributeName, value);
    }

    String getDescription() {
        return description;
    }

    protected Logger getLogger() {
        return LogManager.getLogger(getClass());
    }

    private WebElement getElement() {
        if (parent == null) UiHost.getInstance().selectWindow();
        if (frameId != null) element = getElementBehavior.execute();
        if (element == null) element = getElementBehavior.execute();
        return element;
    }

    private String getIndentation() {
        return new String(new char[4]).replace('\0', ' ');
    }

    private boolean classContains(String state) {
        String className = getClassName();
        if (className == null || state == null) return false;
        return className.contains(state);
    }

    private boolean styleContains(String state) {
        String style = getStyle();
        if (style == null || state == null) return false;
        return style.contains(state);
    }

    private String getStyle() {
        WebElement element = getElement();
        return element == null ? null : element.getAttribute(Attribute.STYLE);
    }

    private String getClassName() {
        WebElement element = getElement();
        return element == null ? null : element.getAttribute(Attribute.CLASS);
    }

    private void initializeGetElementBehavior() {
        if (frameId != null) getElementBehavior = new GetFrame();
        else if (parent == null && attribute == null && ordinal == 1) getElementBehavior = new GetView();
        else if (parent == null) getElementBehavior = new GetView_attribute();
        else if (attribute != null) getElementBehavior = new GetElement_attribute();
        else getElementBehavior = new GetElement();
    }

    private void reportException(WebDriverException e, String errorMessage) {
        getLogger().error(errorMessage);
        e.printStackTrace();
        throw new WebDriverException(errorMessage);
    }

    private String getOrdinal() {
        return ordinal == 1 ? "" : String.format("#%d", ordinal);
    }

    private String getElementDescription() {
        String elementDescription = "%s element with locator %s %s";
        String attributeDescription = "and '%s' attribute containing \"%s\"";
        return String.format(elementDescription, description, locator.toString(), attribute != null ? String
                .format(attributeDescription, attribute, attributeValue) : "");
    }

    public String getAttribute(String attributeName) {
        return getElement().getAttribute(attributeName);
    }

    private interface GetElementBehavior {
        WebElement execute();
    }

    private interface ElementBehavior {
        void set(String text);

        String getText();
    }

    /**
     * ** IS CLICKABLE BEHAVIORS ****
     */
    private interface IsClickableBehavior {
        boolean execute();
    }

    public class LocatorType {

        public static final String CLASS = "class";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TAG = "tag";
        public static final String FRAME = "frame";
    }

    public class Attribute {

        public static final String STYLE = "style";
        public static final String CLASS = "class";
        private static final String HREF = "href";
        private static final String SRC = "src";
    }

    /**
     * ** GET ELEMENT BEHAVIORS ****
     */
    protected class GetView implements GetElementBehavior {

        public WebElement execute() {
            UiHost host = UiHost.getInstance();
            return host.findUiElement(locator);
        }
    }

    protected class GetView_attribute implements GetElementBehavior {

        public WebElement execute() {
            UiHost host = UiHost.getInstance();
            List<WebElement> candidates = host.findUiElements(locator);
            for (WebElement candidate : candidates) {
                final String candidateAttributeValue = candidate.getAttribute(attribute);
                if (candidateAttributeValue != null && candidateAttributeValue.contains(attributeValue))
                    return candidate;
            }
            return null;
        }
    }

    protected class GetFrame implements GetElementBehavior {

        @Override
        public WebElement execute() {
            UiHost.getInstance().selectFrame(frameId);
            return UiHost.getInstance().findUiElement(locator);
        }
    }

    protected class GetElement implements GetElementBehavior {

        public WebElement execute() {
            List<WebElement> elements;
            int elementIndex = ordinal - 1;
            try {
                final WebElement parentElement = parent.getElement();
                elements = parentElement == null ? new ArrayList<>() : parentElement.findElements(locator);
            } catch (WebDriverException | NullPointerException e) {
                getLogger().warn(String.format(message_unableToFind, getElementDescription()));
                return null;
            }
            if (elements.size() > elementIndex) {
                return elements.get(elementIndex);
            } else {
                getLogger().warn(String.format(message_unableToFind, getElementDescription()));
            }
            return null;
        }
    }

    protected class GetElement_attribute implements GetElementBehavior {

        public WebElement execute() {
            List<WebElement> elements = new ArrayList<>();
            int elementIndex = ordinal - 1;
            try {
                final WebElement parentElement = parent.getElement();
                List<WebElement> candidates = parentElement == null ? new ArrayList<WebElement>() : parentElement
                        .findElements(locator);
                for (WebElement candidate : candidates) {
                    final String candidateAttributeValue = candidate.getAttribute(attribute);
                    if (candidateAttributeValue != null && candidateAttributeValue.contains(attributeValue))
                        elements.add(candidate);
                }
            } catch (WebDriverException e) {
                getLogger().warn(String.format(message_unableToFind, getElementDescription()));
                return null;
            } catch (NullPointerException e) {
                getLogger().warn(String.format(message_unableToFind, getElementDescription()));
                return null;
            }
            if (elements.size() > elementIndex) {
                return elements.get(elementIndex);
            } else {
                getLogger().warn(String.format(message_unableToFind, getElementDescription()));
            }
            return null;
        }
    }

    /**
     * ** SET ELEMENT BEHAVIORS ****
     */
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
            return element == null ? null : element.getTagName().equals("input") ? element.getAttribute("value") : element
                    .getText();
        }
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
}
