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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorAttributeOrdinalParent extends WebGetElementBehavior {
    private final String attributeValue;
    private final String attribute;
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;

    private WebGetElementByLocatorAttributeOrdinalParent(
            By locator, String attribute, String attributeValue,
            Integer ordinal, WebGetElementBehavior getParent) {
        super(locator);
        this.attributeValue = attributeValue;
        this.attribute = attribute;
        this.ordinal = ordinal;
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetElementByLocatorAttributeOrdinalParent getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetElementByLocatorAttributeOrdinalParent(
                WebUiLocator.getInstance(locatorType, locatorValue), attribute, attributeValue, ordinal, getParent);
    }

    @Override
    public WebElement execute() {
        List<WebElement> candidates;
        if (getParent == null) {
            candidates = WebUiHost.getInstance().findUiElements(locator);
        } else {
            candidates = getParent.execute().findElements(locator);
        }
        Integer ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    return candidate;
                }
            }
        }
        return null;
    }
}
