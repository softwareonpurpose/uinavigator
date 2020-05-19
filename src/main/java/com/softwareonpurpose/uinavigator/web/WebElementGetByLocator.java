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

public class WebElementGetByLocator extends WebElementGet {
    private transient WebElement element;

    private WebElementGetByLocator(String description, UiLocatorType locatorType, String locatorValue, UiHost host) {
        super(description, locatorType, locatorValue, host);
    }

    public static WebElementGetByLocator getInstance(String description, UiLocatorType locatorType, String locatorValue, UiHost host) {
        return new WebElementGetByLocator(description, locatorType, locatorValue, host);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            element = (WebElement) host.findElement(super.locator);
        }
        return element;
    }
}
