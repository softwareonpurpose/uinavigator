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

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttributeParent implements WebGetListBehavior {
    private final String attribute;
    private final String attributeValue;
    private final WebGetElementBehavior getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorAttributeParent(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, WebGetElementBehavior getParent) {
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetListByLocatorAttributeParent getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorAttributeParent(locatorType, locatorValue, attribute, attributeValue, getParent);
    }

    @Override
    public Collection<UiElement> execute() {
        List<UiElement> elements = new ArrayList<>();
        List<WebElement> candidates;
        By locator = WebUiLocator.getInstance(locatorType, locatorValue);
        if (getParent == null) {
            candidates = WebUiHost.getInstance().findUiElements(locator);
        } else {
            candidates = ((WebElement) getParent.execute()).findElements(locator);
        }
        int ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                final String description = String.format("#%d", ordinal);
                elements.add(WebUiElement.getInstance(description, locatorType, locatorValue, this.attribute, this.attributeValue));
            }
        }
        return elements;
    }
}
