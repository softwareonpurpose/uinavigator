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

public class WebGetElementListByLocator extends UiElementGetList {
    private final String locatorType;
    private final String locatorValue;
    private final String description;

    private WebGetElementListByLocator(String description, String locatorType, String locatorValue, UiDriverGet getDriver) {
        super(getDriver);
        this.description = description;
        this.locatorValue = locatorValue;
        this.locatorType = locatorType;
    }

    public static WebGetElementListByLocator getInstance(String description, String locatorType, String locatorValue, UiDriverGet getDriver) {
        return new WebGetElementListByLocator(description, locatorType, locatorValue, getDriver);
    }

    @Override
    public Collection<UiElement> execute() {
        Collection<UiElement> elements = new ArrayList<>();
        Collection<Object> webElements = new ArrayList<>();
        By locator = WebElementLocator.getInstance(locatorType, locatorValue);
        if (new By.ByTagName("body").equals(locator)) {
            webElements.addAll(UiDriverFindElements.getInstance(getDriver).execute(locator));
        } else {
            WebElementGetByLocator getParent = WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, "body", getDriver);
            webElements.addAll(getParent.execute().findElements(locator));
        }
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal += 1) {
            String elementDescription = String.format("#%d", elementOrdinal);
            elements.add(UiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal));
        }
        return elements;
    }
}
