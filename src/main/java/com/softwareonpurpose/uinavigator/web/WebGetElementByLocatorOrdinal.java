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

public class WebGetElementByLocatorOrdinal extends WebGetElementBehavior {
    private final Integer ordinal;

    private WebGetElementByLocatorOrdinal(String description, By locator, Integer ordinal) {
        super(description, locator);
        this.ordinal = ordinal;
    }

    public static WebGetElementBehavior getInstance(
            String description, String locatorType, String locatorValue, Integer ordinal) {
        return new WebGetElementByLocatorOrdinal(
                description, WebUiLocator.getInstance(locatorType, locatorValue), ordinal);
    }

    @Override
    public WebElement execute() {
        final List<WebElement> elements = WebUiHost.getInstance().findUiElements(locator);
        return elements.size() >= ordinal ? elements.get(ordinal - 1) : null;
    }
}
