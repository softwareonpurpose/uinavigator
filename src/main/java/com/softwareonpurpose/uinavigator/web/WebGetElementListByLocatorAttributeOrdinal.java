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
import com.softwareonpurpose.uinavigator.UiElementGetList;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetElementListByLocatorAttributeOrdinal extends UiElementGetList {
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final UiLocatorType locatorType;
    private final String locatorValue;

    private WebGetElementListByLocatorAttributeOrdinal(UiLocatorType locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiHost host) {
        super(host);
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetElementListByLocatorAttributeOrdinal getInstance(
            UiLocatorType locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal, UiHost host) {
        return new WebGetElementListByLocatorAttributeOrdinal(locatorType, locatorValue, attribute, attributeValue, ordinal, host);
    }

    @Override
    public Collection<UiElement> execute() {
        List<UiElement> elements = new ArrayList<>();
        By locator = WebElementLocator.getInstance(locatorType, locatorValue);
        List<Object> candidates = new ArrayList<>(host.findElements(locator));
        Integer ordinal = 0;
        for (Object candidate : candidates) {
            final WebElement webCandidate = (WebElement) candidate;
            final String attributeValue = webCandidate.getAttribute(this.attribute);
            if (attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    final String description = String.format("#%d", ordinal);
                    elements.add(UiElement.getInstance(
                            description, locatorType, locatorValue, this.attribute, this.attributeValue, ordinal, host));
                    return elements;
                }
            }
        }
        return elements;
    }
}
