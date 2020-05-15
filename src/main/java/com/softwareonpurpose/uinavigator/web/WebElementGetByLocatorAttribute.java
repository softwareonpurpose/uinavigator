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

import com.softwareonpurpose.uinavigator.UiDriverFindElements;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementGetByLocatorAttribute extends WebElementGet {
    private final String attribute;
    private final String attributeValue;
    private transient WebElement element;

    private WebElementGetByLocatorAttribute(String description, By locator, String attribute, String attributeValue, UiDriverGet getDriver) {
        super(description, locator, getDriver);
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }

    public static WebElementGetByLocatorAttribute getInstance(
            String description, String locatorType, String locatorValue, String attribute, String attributeValue, UiDriverGet getDriver) {
        return new WebElementGetByLocatorAttribute(
                description, WebElementLocator.getInstance(locatorType, locatorValue), attribute, attributeValue, getDriver);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            List<Object> candidates = UiDriverFindElements.getInstance(getDriver).execute(locator);
            List<WebElement> elements = new ArrayList<>();
            for (Object candidate : candidates) {
                final WebElement webCandidate = (WebElement) candidate;
                final String attributeValue = webCandidate.getAttribute(this.attribute);
                if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                    elements.add(webCandidate);
                }
            }
            element = elements.size() == 0 ? null : elements.get(0);
        }
        return element;
    }
}
