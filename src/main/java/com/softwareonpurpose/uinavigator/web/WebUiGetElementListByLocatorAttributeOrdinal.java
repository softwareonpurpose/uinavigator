package com.softwareonpurpose.uinavigator.web;
/*
  Copyright 2020 - 2022 Craig A. Stockton
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebUiGetElementListByLocatorAttributeOrdinal implements WebUiGetElementList {
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final String locatorType;
    private final String locatorValue;

    private WebUiGetElementListByLocatorAttributeOrdinal(String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebUiGetElementListByLocatorAttributeOrdinal getInstance(
            String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        return new WebUiGetElementListByLocatorAttributeOrdinal(locatorType, locatorValue, attribute, attributeValue, ordinal);
    }

    @Override
    public Collection<UiElement> execute() {
        List<UiElement> elements = new ArrayList<>();
        By locator = WebElementLocator.getInstance(locatorType, locatorValue);
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        Integer ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    elements.add(UiElement.getInstance(
                            String.format("#%d", ordinal), locatorType, locatorValue, this.attribute, this.attributeValue, ordinal));
                    return elements;
                }
            }
        }
        return elements;
    }
}
