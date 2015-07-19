package com.craigstockton.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HtmlElement {

    private static String message_unableToFind = "WARNING: Unable to locate %s";
    private final HtmlElement parent;
    private final String description;
    private final String attribute;
    private final String attributeValue;
    private final int ordinal;
    private String activeClass;
    private String selectedClass;
    private String tipAttribute = "title";
    private By locator;
    private GetElementBehavior getElementBehavior;

    public class LocatorType {

        public static final String CLASS = "class";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TAG = "tag";
    }

    public class Attribute {

        public static final String STYLE = "style";
        private static final String HREF = "href";
        private static final String SRC = "src";
    }

    private HtmlElement(String description, String locatorType, String locatorValue, String attribute, String attributeValue,
            Integer ordinal, HtmlElement parent) {
        this.description = description;
        locator = createLocator(locatorType, locatorValue);
        this.parent = parent;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal == null ? 1 : ordinal;
        initializeGetElementBehavior();
    }

    public static HtmlElement getInstance(String description, String locatorType, String locatorValue) {
        return new HtmlElement(description, locatorType, locatorValue, null, null, null, null);
    }

    public static HtmlElement getInstance(String description, String locatorType, String locatorValue, HtmlElement parent) {
        return new HtmlElement(description, locatorType, locatorValue, null, null, null, parent);
    }

    public static HtmlElement getInstance(String description, String locatorType, String locatorValue, int ordinal,
            HtmlElement parent) {
        return new HtmlElement(description, locatorType, locatorValue, null, null, ordinal, parent);
    }

    public static HtmlElement getInstance(String description, String locatorType, String locatorValue, String attribute,
            String attributeValue, HtmlElement parent) {
        return new HtmlElement(description, locatorType, locatorValue, attribute, attributeValue, null, parent);
    }

    public String getText() {
        final WebElement element = getElement();
        return element == null ? null : element.getTagName().equals("input") ? element.getAttribute("value") : element.getText();
    }

    public String getHref() {
        final WebElement element = getElement();
        return element == null ? null : element.getAttribute(Attribute.HREF);
    }

    public String getTip() {
        final WebElement element = getElement();
        return element == null ? null : tipAttribute == null ? null : element.getAttribute(tipAttribute);
    }

    public HtmlElement setTipAttribute(String attribute) {
        this.tipAttribute = attribute;
        return this;
    }

    public void set(String value) {
        value = value == null ? "" : value;
        WebElement element = getElement();
        getLogger().info(String.format(getIndentation() + "Set %s to \"%s\"", getDescription(), value));
        String message_unableToSet = "BLOCKED: Unable to set %s element to \"%s\"";
        if (element != null) {
            try {
                element.clear();
                getElement().sendKeys(value);
            } catch (WebDriverException e) {
                final String errorMessage = String.format(message_unableToSet, locator.toString(), value);
                reportException(e, errorMessage);
            }
        } else {
            getLogger().error(String.format(message_unableToSet, locator.toString(), value));
        }
    }

    public void click() {
        getLogger().info(String.format(getIndentation() + "Click %s", getDescription()));
        WebElement element = getElement();
        final String errorMessage = String.format("BLOCKED: Unable to click %s", getElementDescription());
        if (element != null) {
            try {
                element.click();
            } catch (WebDriverException e) {
                reportException(e, errorMessage);
            }
        } else {
            getLogger().error(errorMessage);
        }
    }

    public boolean isActive() {
        return classContains(activeClass);
    }

    public boolean isSelected() {
        return selectedClass == null ? getElement().isSelected() : classContains(selectedClass);
    }

    public boolean isDisplayed() {
        return getElement() != null;
    }

    public HtmlElement setSelectedClass(String selectedClass) {
        this.selectedClass = selectedClass;
        return this;
    }

    public HtmlElement setActiveClass(String activeClass) {
        this.activeClass = activeClass;
        return this;
    }

    public boolean waitUntilVisible() {
        getLogger().info(String.format("Expect %s", getElementDescription()));
        return BrowserHost.getInstance().waitUntilVisible(locator);
    }

    public static List<HtmlElement> getList(String description, String locatorType, String locatorValue, HtmlElement parent) {
        List<HtmlElement> htmlElements = new ArrayList<>();
        List<WebElement> webElements = parent.getElement().findElements(createLocator(locatorType, locatorValue));
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal++) {
            htmlElements.add(HtmlElement.getInstance(description, locatorType, locatorValue, elementOrdinal, parent));
        }
        return htmlElements;
    }

    public String getSrc() {
        final WebElement element = getElement();
        return element == null ? null : element.getAttribute(Attribute.SRC);
    }

    protected Logger getLogger() {
        return LogManager.getLogger(getClass());
    }

    private static By createLocator(String locatorType, String locatorValue) {
        By locator = null;
        switch (locatorType) {
            case LocatorType.CLASS:
                locator = By.className(locatorValue);
                break;
            case LocatorType.ID:
                locator = By.id(locatorValue);
                break;
            case LocatorType.NAME:
                locator = By.name(locatorValue);
                break;
            case LocatorType.TAG:
                locator = By.tagName(locatorValue);
                break;
        }
        return locator;
    }

    private String getDescription() {
        return description;
    }

    private WebElement getElement() {
        return getElementBehavior.execute();
    }

    private String getIndentation() {
        return new String(new char[4]).replace('\0', ' ');
    }

    private boolean classContains(String state) {
        state = state == null ? "" : state;
        String className = getClassName();
        return className != null && className.contains(state);
    }

    private String getClassName() {
        return getElement() == null ? null : getElement().getAttribute(LocatorType.CLASS);
    }

    private void initializeGetElementBehavior() {
        if (parent == null && attribute == null)
            getElementBehavior = new GetView();
        else if (parent == null)
            getElementBehavior = new GetView_attribute();
        else if (attribute != null)
            getElementBehavior = new GetElement_attribute();
        else
            getElementBehavior = new GetElement();
    }

    private void reportException(WebDriverException e, String errorMessage) {
        getLogger().error(errorMessage);
        e.printStackTrace();
        throw new WebDriverException(errorMessage);
    }

    private String getOrdinal() {
        return ordinal == 1 ? "" : String.format("#%d", ordinal);
    }

    private interface GetElementBehavior {

        WebElement execute();
    }

    private String getElementDescription() {
        String elementDescription = "%s element with locator %s %s";
        String attributeDescription = "and '%s' attribute containing \"%s\"";
        return String.format(elementDescription, description, locator.toString(),
                attribute != null ? String.format(attributeDescription, attribute, attributeValue) : "");
    }

    /**
     * ** GET ELEMENT BEHAVIOR ****
     */
    protected class GetView implements GetElementBehavior {

        public WebElement execute() {
            return BrowserHost.getInstance().findUiElement(locator);
        }
    }

    protected class GetView_attribute implements GetElementBehavior {

        public WebElement execute() {
            List<WebElement> candidates = BrowserHost.getInstance().findUiElements(locator);
            for (WebElement candidate : candidates) {
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
            int elementIndex = ordinal - 1;
            try {
                final WebElement parentElement = parent.getElement();
                elements = parentElement == null ? new ArrayList<>() : parentElement.findElements(locator);
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

    protected class GetElement_attribute implements GetElementBehavior {

        public WebElement execute() {
            List<WebElement> elements;
            elements = new ArrayList<>();
            int elementIndex = ordinal - 1;
            try {
                final WebElement parentElement = parent.getElement();
                List<WebElement> candidates = parentElement == null ? new ArrayList<>() : parentElement.findElements(locator);
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
}
