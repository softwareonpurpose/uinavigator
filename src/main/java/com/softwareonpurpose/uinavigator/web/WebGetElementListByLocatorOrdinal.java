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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetElementListByLocatorOrdinal extends UiElementGetList {
    private final Integer ordinal;
    private final UiLocatorType locatorType;
    private final String locatorValue;

    private WebGetElementListByLocatorOrdinal(UiLocatorType locatorType, String locatorValue, Integer ordinal, UiHost host) {
        super(host);
        this.ordinal = ordinal;
        this.locatorValue = locatorValue;
        this.locatorType = locatorType;
    }

    public static WebGetElementListByLocatorOrdinal getInstance(UiLocatorType locatorType, String locatorValue, Integer ordinal, UiHost host) {
        return new WebGetElementListByLocatorOrdinal(locatorType, locatorValue, ordinal, host);
    }

    @Override
    public Collection<UiElement> execute() {
        List<UiElement> elements = new ArrayList<>();
        By locator = WebElementLocator.getInstance(locatorType, locatorValue);
        List<Object> candidates = new ArrayList<>(host.findElements(locator));
        if (candidates.size() >= ordinal) {
            elements.add(UiElement.getInstance(String.format("#%d", ordinal), locatorType, locatorValue, ordinal, host));
        }
        return elements;
    }
}
