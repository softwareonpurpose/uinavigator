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
import org.openqa.selenium.support.ByIdOrName;

public class WebElementLocator {
    public static By getInstance(String locatorType, String locatorValue) {
        if (locatorType == null || locatorValue == null) {
            return null;
        }
        By locator;
        switch (locatorType) {
            case UiLocatorType.CLASS:
                locator = new By.ByClassName(locatorValue);
                break;
            case UiLocatorType.TAG:
                locator = new By.ByTagName(locatorValue);
                break;
            case UiLocatorType.ID:
            case UiLocatorType.NAME:
            default:
                locator = new ByIdOrName(locatorValue);
        }
        return locator;
    }
}
