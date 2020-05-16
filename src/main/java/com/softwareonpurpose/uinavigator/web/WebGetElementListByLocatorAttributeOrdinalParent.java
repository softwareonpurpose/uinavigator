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

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetElementListByLocatorAttributeOrdinalParent extends UiElementGetList {
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final WebElementGet getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebGetElementListByLocatorAttributeOrdinalParent(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebElementGet getParent, UiHost host) {
        super(host);
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetElementListByLocatorAttributeOrdinalParent getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebElementGet getParent, UiHost host) {
        return new WebGetElementListByLocatorAttributeOrdinalParent(
                locatorType, locatorValue, attribute, attributeValue, ordinal, getParent, host);
    }

    @Override
    public Collection<UiElement> execute() {
        List<UiElement> elements = new ArrayList<>();
        List<Object> candidates = new ArrayList<>();
        By locator = WebElementLocator.getInstance(locatorType, locatorValue);
        if (getParent == null) {
            candidates.addAll(host.findElements(locator));
        } else {
            candidates.addAll((getParent.execute()).findElements(locator));
        }
        Integer ordinal = 0;
        for (Object candidate : candidates) {
            final WebElement webCandidate = (WebElement) candidate;
            final String attributeValue = webCandidate.getAttribute(this.attribute);
            if (attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    elements.add(UiElement.getInstance(
                            String.format("#%d", ordinal), locatorType, locatorValue,
                            this.attribute, this.attributeValue, ordinal, host));
                    return elements;
                }
            }
        }
        return elements;
    }
}
