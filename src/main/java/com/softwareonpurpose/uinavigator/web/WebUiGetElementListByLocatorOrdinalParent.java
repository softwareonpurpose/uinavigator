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

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebUiGetElementListByLocatorOrdinalParent implements WebUiGetElementList {
    private final Integer ordinal;
    private final WebUiGetElement getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebUiGetElementListByLocatorOrdinalParent(String locatorType, String locatorValue, Integer ordinal, WebUiGetElement getParent) {
        this.ordinal = ordinal;
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebUiGetElementListByLocatorOrdinalParent getInstance(
            String locatorType, String locatorValue, Integer ordinal, WebUiGetElement getParent) {
        return new WebUiGetElementListByLocatorOrdinalParent(locatorType, locatorValue, ordinal, getParent);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        List<WebElement> candidates;
        By locator = WebElementLocator.getInstance(locatorType, locatorValue);
        if (getParent == null) {
            candidates = WebUiHost.getInstance().findUiElements(locator);
        } else {
            candidates = (getParent.execute()).findElements(locator);
        }
        if (candidates.size() >= ordinal) {
            elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locatorType, locatorValue, ordinal));
        }
        return elements;
    }
}
