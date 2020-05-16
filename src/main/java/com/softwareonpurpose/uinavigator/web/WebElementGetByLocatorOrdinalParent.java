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

public class WebElementGetByLocatorOrdinalParent extends WebElementGet {
    private final Integer ordinal;
    private final WebElementGet getParent;
    private transient WebElement element;

    private WebElementGetByLocatorOrdinalParent(
            String description, By locator, Integer ordinal, WebElementGet getParent, UiHost host) {
        super(description, locator, host);
        this.ordinal = ordinal;
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebElementGetByLocatorOrdinalParent getInstance(
            String description, String locatorType, String locatorValue,
            Integer ordinal, WebElementGet getParent, UiHost host) {
        return new WebElementGetByLocatorOrdinalParent(
                description, WebElementLocator.getInstance(locatorType, locatorValue), ordinal, getParent, host);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            List<Object> elements = new ArrayList<>();
            if (getParent == null) {
                elements.addAll(host.findElements(locator));
            } else {
                elements.addAll((getParent.execute()).findElements(locator));
            }
            element = elements.size() >= ordinal ? (WebElement) elements.get(ordinal - 1) : null;
        }
        return element;
    }
}
