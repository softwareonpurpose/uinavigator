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

import com.softwareonpurpose.uinavigator.UiElementGet;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementGetByLocatorParent extends WebElementGet {
    private final WebElementGet getParent;
    private transient WebElement element;
    private Boolean isParentOutOfScope;

    private WebElementGetByLocatorParent(String description, UiLocatorType locatorType, String locatorValue, UiElementGet getParent, UiHost host) {
        super(description, locatorType, locatorValue, host);
        this.getParent = (WebElementGet) getParent;
    }

    public static WebElementGetByLocatorParent getInstance(
            String description, UiLocatorType locatorType, String locatorValue, WebElementGet getParent, UiHost host) {
        return new WebElementGetByLocatorParent(
                description, locatorType, locatorValue, getParent, host);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            List<Object> elements = new ArrayList<>();
            if (getParent() == null) {
                elements.addAll(host.findElements(locator));
            } else {
                elements.addAll((getParent()).execute().findElements((By) locator));
            }
            element = elements.size() == 0 ? null : (WebElement) elements.get(0);
        }
        return element;
    }

    private WebElementGet getParent() {
        if (isParentOutOfScope == null) {
            final boolean isLocatorBodyTag = new By.ByTagName("body").equals(locator);
            final WebElement parent = (getParent == null) ? null : getParent.execute();
            final boolean isParentIFrame = (parent != null) && "iframe".equals(parent.getTagName());
            isParentOutOfScope = isLocatorBodyTag || isParentIFrame;
        }
        return isParentOutOfScope ? null : getParent;
    }
}
