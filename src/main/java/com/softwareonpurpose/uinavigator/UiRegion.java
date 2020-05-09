package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiElement;
import org.slf4j.LoggerFactory;

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
public abstract class UiRegion {
    private static boolean suppressLogging;
    private final WebUiElement regionElement;

    protected UiRegion(WebUiElement regionElement) {
        this.regionElement = regionElement;
        if (!suppressLogging)
            LoggerFactory.getLogger("").info(String.format("In %s...", getDescription()));
    }

    public static void suppressConstructionLogging(boolean suppress) {
        suppressLogging = suppress;
    }

    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    public WebUiElement getElement() {
        return regionElement;
    }

    public String getDescription() {
        return getElement().getDescription();
    }
}
