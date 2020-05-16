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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetElementListByLocatorParent extends UiElementGetList {
    private final UiElementGet getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebGetElementListByLocatorParent(String locatorType, String locatorValue, UiElementGet getParent, UiHost host) {
        super(host);
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetElementListByLocatorParent getInstance(
            String locatorType, String locatorValue, UiElementGet getParent, UiHost host) {
        return new WebGetElementListByLocatorParent(locatorType, locatorValue, getParent, host);
    }

    @Override
    public Collection<UiElement> execute() {
        List<UiElement> elements = new ArrayList<>();
        List<Object> candidates = new ArrayList<>();
        By locator = WebElementLocator.getInstance(locatorType, locatorValue);
        if (getParent == null) {
            candidates.addAll(host.findElements(locator));
        } else {
            candidates.addAll(((WebElementGet) getParent).execute().findElements(locator));
        }
        int ordinal = 0;
        //noinspection unused
        for (Object candidate : candidates) {
            ordinal += 1;
            elements.add(UiElement.getInstance(String.format("#%d", ordinal), locatorType, locatorValue, host));
        }
        return elements;
    }
}
