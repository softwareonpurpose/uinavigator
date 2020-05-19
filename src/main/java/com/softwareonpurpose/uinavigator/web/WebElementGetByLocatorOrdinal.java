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
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementGetByLocatorOrdinal extends WebElementGet {
    private final Integer ordinal;
    private transient WebElement element;

    private WebElementGetByLocatorOrdinal(String description, UiLocatorType locatorType, String locatorValue, Integer ordinal, UiHost host) {
        super(description, locatorType, locatorValue, host);
        this.ordinal = ordinal;
    }

    public static WebElementGet getInstance(
            String description, UiLocatorType locatorType, String locatorValue, Integer ordinal, UiHost host) {
        return new WebElementGetByLocatorOrdinal(
                description, locatorType, locatorValue, ordinal, host);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            final List<Object> elements = host.findElements(locator);
            element = elements.size() >= ordinal ? (WebElement) elements.get(ordinal - 1) : null;
        }
        return element;
    }
}
