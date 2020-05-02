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
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;

public class WebGetListByLocator implements WebGetListBehavior {
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocator(String locatorType, String locatorValue) {
        this.locatorValue = locatorValue;
        this.locatorType = locatorType;
    }

    public static WebGetListByLocator getInstance(String locatorType, String locatorValue) {
        return new WebGetListByLocator(locatorType, locatorValue);
    }

    @Override
    public Collection<UiElement> execute() {
        Collection<UiElement> elements = new ArrayList<>();
        Collection<WebElement> webElements;
        By locator = WebUiLocator.getInstance(locatorType, locatorValue);
        if (new By.ByTagName("body").equals(locator)) {
            webElements = WebUiHost.getInstance().findUiElements(locator);
        } else {
            WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "body");
            webElements = getParent.execute().findElements(locator);
        }
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal += 1) {
            String elementDescription = String.format("#%d", elementOrdinal);
            elements.add(WebUiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal));
        }
        return elements;
    }
}
