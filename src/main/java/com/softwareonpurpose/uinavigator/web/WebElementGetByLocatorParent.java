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

import com.softwareonpurpose.uinavigator.UiDriverFindElements;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementGetByLocatorParent extends WebElementGet {
    private final WebElementGet getParent;
    private transient WebElement element;

    private WebElementGetByLocatorParent(String description, By locator, WebElementGet getParent, UiDriverGet getDriver) {
        super(description, locator, getDriver);
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebElementGetByLocatorParent getInstance(
            String description, String locatorType, String locatorValue, WebElementGet getParent, UiDriverGet getDriver) {
        return new WebElementGetByLocatorParent(
                description, WebElementLocator.getInstance(locatorType, locatorValue), getParent, getDriver);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            List<Object> elements = new ArrayList<>();
            if (getParent == null) {
                elements.addAll(UiDriverFindElements.getInstance(getDriver).execute(locator));
            } else {
                elements.addAll((getParent.execute()).findElements(locator));
            }
            element = elements.size() == 0 ? null : (WebElement) elements.get(0);
        }
        return element;
    }
}
