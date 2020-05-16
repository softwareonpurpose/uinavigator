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

import com.softwareonpurpose.uinavigator.UiHost;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementGetByLocatorAttributeOrdinalParent extends WebElementGet {
    private final String attributeValue;
    private final String attribute;
    private final Integer ordinal;
    private final WebElementGet getParent;
    private transient WebElement element;

    private WebElementGetByLocatorAttributeOrdinalParent(
            String description, By locator, String attribute, String attributeValue,
            Integer ordinal, WebElementGet getParent, UiHost host) {
        super(description, locator, host);
        this.attributeValue = attributeValue;
        this.attribute = attribute;
        this.ordinal = ordinal;
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebElementGetByLocatorAttributeOrdinalParent getInstance(
            String description, String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebElementGet getParent, UiHost host) {
        return new WebElementGetByLocatorAttributeOrdinalParent(
                description, WebElementLocator.getInstance(locatorType, locatorValue),
                attribute, attributeValue, ordinal, getParent, host);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            List<Object> candidates = new ArrayList<>();
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
                        element = webCandidate;
                    }
                }
            }
        }
        return element;
    }
}
