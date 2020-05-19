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
import com.softwareonpurpose.uinavigator.UiLocatorType;
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
    private Boolean isParentOutOfScope;

    private WebElementGetByLocatorAttributeOrdinalParent(
            String description, UiLocatorType locatorType, String locatorValue, String attribute, String attributeValue,
            Integer ordinal, WebElementGet getParent, UiHost host) {
        super(description, locatorType, locatorValue, host);
        this.attributeValue = attributeValue;
        this.attribute = attribute;
        this.ordinal = ordinal;
        this.getParent = getParent;
    }

    public static WebElementGetByLocatorAttributeOrdinalParent getInstance(
            String description, UiLocatorType locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebElementGet getParent, UiHost host) {
        return new WebElementGetByLocatorAttributeOrdinalParent(
                description, locatorType, locatorValue,
                attribute, attributeValue, ordinal, getParent, host);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            List<Object> candidates = new ArrayList<>();
            if (getParent() == null) {
                candidates.addAll(host.findElements(locator));
            } else {
                candidates.addAll(getParent().execute().findElements((By) locator));
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

    private WebElementGet getParent() {
        if (isParentOutOfScope == null) {
            final boolean isLocatorBodyTag = new By.ByTagName("body").equals(locator);
            final WebElement parent = (getParent == null) ? null : getParent.execute();
            final boolean isParentIFrame = (parent != null) && "iframe".equals(parent.getTagName());
            isParentOutOfScope = isLocatorBodyTag || isParentIFrame;
        }
        return isParentOutOfScope ? null : getParent;
    }
}
