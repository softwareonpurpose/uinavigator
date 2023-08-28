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

public class WebUiGetElementByLocatorParent extends WebUiGetElement {
    private final WebUiGetElement getParent;

    private WebUiGetElementByLocatorParent(String description, By locator, WebUiGetElement getParent) {
        super(description, locator);
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebUiGetElementByLocatorParent getInstance(
            String description, String locatorType, String locatorValue, WebUiGetElement getParent) {
        return new WebUiGetElementByLocatorParent(
                description, WebElementLocator.getInstance(locatorType, locatorValue), getParent);
    }

    @Override
    public WebElement execute() {
        List<WebElement> elements;
        if (getParent == null) {
            elements = WebUiHost.getInstance().findUiElements(locator);
        } else {
            elements = ((WebElement) getParent.execute()).findElements(locator);
        }
        return elements.size() == 0 ? null : elements.get(0);
    }
}
