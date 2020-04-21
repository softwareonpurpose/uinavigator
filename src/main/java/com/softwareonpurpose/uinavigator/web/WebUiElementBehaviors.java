package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetTextBehavior;
import com.softwareonpurpose.uinavigator.SetElementBehavior;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class WebUiElementBehaviors {
    private final WebGetElementBehavior getElement;
    private final WebGetListBehavior getList;
    private final SetElementBehavior setElement;
    private final GetTextBehavior getText;
    private final WebGetAttributeBehavior getAttribute;
    private final WebIsDisplayedBehavior isDisplayed;
    private final String description;
    private StateBehavior isActive;
    private boolean suppressLogging;
    private StateBehavior isSelected;

    private WebUiElementBehaviors(
            String description, WebGetElementBehavior getElement,
            WebGetListBehavior getList,
            SetElementBehavior setElement,
            GetTextBehavior getText) {
        this.description = description;
        this.getElement = getElement;
        this.getList = getList;
        this.setElement = setElement;
        this.getText = getText;
        this.getAttribute = WebGetAttributeBehavior.getInstance(getElement);
        this.isDisplayed = WebIsDisplayedBehavior.getInstance(getElement);
    }

    static WebUiElementBehaviors getInstanceByLocator(String description, By locator) {
        WebGetElementBehavior getElement = WebGetElementByLocator.getInstance(locator);
        WebGetListBehavior getList = WebGetListByLocator.getInstance(locator);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttribute(String description, By locator, String attribute, String attributeValue) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttribute.getInstance(locator, attribute, attributeValue);
        WebGetListBehavior getList = WebGetListByLocatorAttribute.getInstance(locator, attribute, attributeValue);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorOrdinal(String description, By locator, Integer ordinal) {
        WebGetElementBehavior getElement = WebGetElementByLocatorOrdinal.getInstance(locator, ordinal);
        WebGetListBehavior getList = WebGetListByLocatorOrdinal.getInstance(locator, ordinal);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorParent(String description, By locator, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement = WebGetElementByLocatorParent.getInstance(locator, getParent);
        WebGetListBehavior getList = WebGetListByLocatorParent.getInstance(locator, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeOrdinal(
            String description, By locator, String attribute, String attributeValue, Integer ordinal) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeParent(
            String description, By locator, String attribute, String attributeValue, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorOrdinalParent(
            String description, By locator, Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(locator, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorOrdinalParent.getInstance(locator, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    static WebUiElementBehaviors getInstanceByLocatorAttributeOrdinalParent(
            String description, By locator, String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        WebGetElementBehavior getElement =
                WebGetElementByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, getParent);
        WebGetListBehavior getList =
                WebGetListByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, getParent);
        SetElementBehavior set = getSetBehavior(locator, getElement);
        GetTextBehavior getText = getGetTextBehavior(locator, getElement);
        return new WebUiElementBehaviors(description, getElement, getList, set, getText);
    }

    private static GetTextBehavior getGetTextBehavior(By locator, WebGetElementBehavior getBehavior) {
        if (new By.ByTagName("select").equals(locator)) {
            return WebGetTextSelectBehavior.getInstance(getBehavior);
        } else {
            return WebGetTextDefaultBehavior.getInstance(getBehavior);
        }
    }

    private static SetElementBehavior getSetBehavior(By locator, WebGetElementBehavior getBehavior) {
        if (new By.ByTagName("select").equals(locator)) {
            return WebSetSelectBehavior.getInstance(getBehavior);
        } else {
            return WebSetDefaultBehavior.getInstance(getBehavior);
        }
    }

    Object get() {
        return getElement.execute();
    }

    Collection getList() {
        return getList.execute();
    }

    String getText() {
        return getText.execute();
    }

    void set(String value) {
        setElement.execute(value);
    }

    public String getAttribute(String attribute) {
        return getAttribute.execute(attribute);
    }

    boolean isDisplayed() {
        return isDisplayed.execute();
    }

    void setActiveBehavior(String attribute, String value) {
        isActive = StateBehavior.getInstance(getElement, attribute, value);
    }

    void setSelectedBehavior(String attribute, String value) {
        isSelected = StateBehavior.getInstance(getElement, attribute, value);
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger("");
    }

    private String getIndentation() {
        return new String(new char[4]).replace('\0', ' ');
    }

    private String getDescription() {
        return description;
    }

    private void reportException(Exception e, String errorMessage) {
        getLogger().error(errorMessage);
        e.printStackTrace();
        throw new WebDriverException(errorMessage);
    }

    void click() {
        if (!suppressLogging) {
            getLogger().info(String.format(getIndentation() + "Click %s", getDescription()));
        }
        WebElement element = getElement.execute();
        final String errorMessage = String.format("BLOCKED: Unable to click %s using hierarchy %s", getDescription(), this.toString());
        if (element != null && !"".equals(element.getTagName())) {
            try {
                element.click();
            } catch (WebDriverException | NullPointerException e) {
                reportException(e, errorMessage);
            }
        } else {
            if (!suppressLogging) {
                getLogger().error(errorMessage);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean isSelected() {
        return isSelected.execute();
    }

    boolean isActive() {
        return isActive.execute();
    }
}
