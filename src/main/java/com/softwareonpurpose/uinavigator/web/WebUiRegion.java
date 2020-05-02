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
import com.softwareonpurpose.uinavigator.UiRegion;
import org.slf4j.LoggerFactory;

/**
 * Region of a UI view
 */
@SuppressWarnings("unused")
public abstract class WebUiRegion implements UiRegion {
    private static boolean suppressLogging;
    private final WebUiElement regionElement;

    protected WebUiRegion(WebUiElement regionElement) {
        this.regionElement = regionElement;
        if (!suppressLogging)
            LoggerFactory.getLogger("").info(String.format("In %s...", getDescription()));
    }

    /***
     * Suppress logging of construction
     * @param suppress boolean log construction
     */
    public static void suppressConstructionLogging(boolean suppress) {
        suppressLogging = suppress;
    }

    /***
     * 'Visible' state
     * @return boolean
     */
    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    /***
     * Get UI element defining UI region
     * @return WebUiElement region
     */
    @SuppressWarnings("WeakerAccess")
    protected WebUiElement getElement() {
        return regionElement;
    }

    /***
     * Description
     * @return String description of UI region
     */
    @SuppressWarnings("WeakerAccess")
    protected String getDescription() {
        return getElement().getDescription();
    }
}
