/*
  Copyright 2019 Craig A. Stockton
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
package com.softwareonpurpose.uinavigator;

import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public abstract class UiRegion {

    private static boolean suppressLogging;
    private final UiElement regionElement;

    /***
     * Super constructor of UiRegion classes
     * @param regionElement UiElement fully containing the region
     */
    protected UiRegion(UiElement regionElement) {
        this.regionElement = regionElement;
        if (!suppressLogging)
            LoggerFactory.getLogger("").info(String.format("In %s...", getDescription()));
    }

    /***
     * Used to suppress construction logging when it would be out of context
     * @param suppress boolean True = log construction of the region (i.e. "In [region]..."); False = suppress logging
     */
    public static void suppressConstructionLogging(boolean suppress) {
        suppressLogging = suppress;
    }

    /***
     * Is the region visible within the parent UiElement
     * @return boolean
     */
    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    /***
     * UiElement fully containing the defined region
     * @return UiElement
     */
    @SuppressWarnings("WeakerAccess")
    protected UiElement getElement() {
        return regionElement;
    }

    /***
     * Description of the region
     * @return String
     */
    @SuppressWarnings("WeakerAccess")
    protected String getDescription() {
        return getElement().getDescription();
    }
}
